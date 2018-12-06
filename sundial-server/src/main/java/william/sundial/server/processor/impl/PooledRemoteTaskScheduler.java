package william.sundial.server.processor.impl;

import william.sundial.common.constant.RemoteTaskType;
import william.sundial.common.dto.TaskInfoDto;
import william.sundial.common.mq.MQDestinations;
import william.sundial.core.command.ExecuteTaskCommand;
import william.sundial.core.processor.RemoteCommandDispatcher;
import william.sundial.core.remote.RemoteMQQueue;
import william.sundial.server.processor.RemoteTaskScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.Assert;
import java.util.Date;
import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 13:37
 * @Description:Schedule Remote Tasks through Thread Pool
 */
public class PooledRemoteTaskScheduler implements RemoteTaskScheduler{
    private static final Logger logger = LoggerFactory.getLogger(PooledRemoteTaskScheduler.class);

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private RemoteCommandDispatcher remoteCommandDispatcher;

    @Override
    public void addNewTasks(List<TaskInfoDto> taskInfoDtos) {
        Assert.notEmpty(taskInfoDtos,"Empty Remote Tasks!!");
        taskInfoDtos.stream().forEach(this::processNewTasks);
    }

    private void processNewTasks(TaskInfoDto taskInfoDto){
        try {
            RemoteMQQueue remoteMQQueue = new RemoteMQQueue(MQDestinations.Queues.EXECUTE_TASK_QUEUE_PREFIX + taskInfoDto.getServiceName());
            ExecuteTaskCommand executeTaskCommand = new ExecuteTaskCommand();
            executeTaskCommand.setTaskKey(taskInfoDto.getTaskKey());

            Runnable runnable = () -> remoteCommandDispatcher.dispatchRemoteCommand(remoteMQQueue,executeTaskCommand);
            if (taskInfoDto.getTaskTypeValue() == RemoteTaskType.FIXED_RATE_TASK.getValue()){
                long initDelayMillis = taskInfoDto.getInitDelayMillis();
                threadPoolTaskScheduler.scheduleAtFixedRate(runnable,new Date(System.currentTimeMillis() + initDelayMillis),taskInfoDto.getFixedRateMillis());
                return;
            }

            if (taskInfoDto.getTaskTypeValue() == RemoteTaskType.FIXED_DELAY_TASK.getValue()){
                long initDelayMillis = taskInfoDto.getInitDelayMillis();
                threadPoolTaskScheduler.scheduleWithFixedDelay(runnable,new Date(System.currentTimeMillis() + initDelayMillis),taskInfoDto.getFixedDelayMillis());
                return;
            }

            if (taskInfoDto.getTaskTypeValue() == RemoteTaskType.CRON_TASK.getValue()){
                threadPoolTaskScheduler.schedule(runnable,new CronTrigger(taskInfoDto.getCronExpression()));
                return;
            }

            logger.error("Unrecognized Task Type!! taskKey: {},taskName: {},taskType: {}",taskInfoDto.getTaskKey(),taskInfoDto.getTaskName(),taskInfoDto.getTaskTypeValue());
        }catch (Exception e){
            logger.error("Process New Task Error!! taskKey: " + taskInfoDto.getTaskKey() + ", taskName: " + taskInfoDto.getTaskName(),e);
        }

    }
}
