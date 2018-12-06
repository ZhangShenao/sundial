package william.sundial.client.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.support.MethodInvokingRunnable;
import org.springframework.util.Assert;
import william.sundial.common.dto.TaskResultDto;
import william.sundial.common.mq.MQDestinations;
import william.sundial.core.command.RecordTaskResultCommand;
import william.sundial.core.processor.EmptyTaskArgumentsProvider;
import william.sundial.core.processor.MQRemoteCommandDispatcher;
import william.sundial.core.processor.RemoteTaskExecutor;
import william.sundial.core.processor.TaskArgumentsProvider;
import william.sundial.core.remote.RemoteMQQueue;
import william.sundial.core.task.definition.RemoteTask;
import william.sundial.core.task.metadata.RemoteTaskMetaData;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/26 15:02
 * @Description:Execute Remote Task through Thread Pool Asynchronously
 */
public class PooledAsyncRemoteTaskExecutor implements RemoteTaskExecutor {
    @Autowired
    private MQRemoteCommandDispatcher remoteCommandDispatcher;

    @Autowired
    private ApplicationContext applicationContext;

    private static final Logger logger = LoggerFactory.getLogger(PooledAsyncRemoteTaskExecutor.class);

    @Override
    public void executeRemoteTask(RemoteTask remoteTask) {
        RemoteTaskMetaData metaData = remoteTask.getMetaData();
        Assert.notNull(metaData,"Remote Task has no MetaData!! taskKey: " + remoteTask.getTaskKey() + ", taskName: " + remoteTask.getTaskName());
        MethodInvokingRunnable methodInvokingRunnable = new MethodInvokingRunnable();
        methodInvokingRunnable.setTargetMethod(metaData.getTargetMethod().getName());
        methodInvokingRunnable.setTargetObject(metaData.getInvokableTarget());
        methodInvokingRunnable.setTargetClass(metaData.getTargetClass());
        if (!(EmptyTaskArgumentsProvider.class.equals(metaData.getArgumentProviderType()))){
            TaskArgumentsProvider paramProvider = applicationContext.getBean(metaData.getArgumentProviderType());
            if (paramProvider != null){
                methodInvokingRunnable.setArguments(paramProvider.provideTaskArguments());
            }
        }

        CompletableFuture.runAsync(() -> {
            RecordTaskResultCommand command = new RecordTaskResultCommand();
            command.setRemoteTask(remoteTask);
            try {
                methodInvokingRunnable.prepare();
                methodInvokingRunnable.run();
                command.setResultDto(TaskResultDto.success(null));
            }catch (Exception e){
                logger.error("Run Remote Task Error!! method: " + methodInvokingRunnable.getTargetMethod());
                command.setResultDto(TaskResultDto.fail(e));
            }finally {
                remoteCommandDispatcher.dispatchRemoteCommand(new RemoteMQQueue(MQDestinations.Queues.RECORD_TASK_RESULT_QUEUE),command);
            }
        });
    }

}
