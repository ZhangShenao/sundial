package william.sundial.demo.server.service.impl;

import william.sundial.common.constant.RemoteTaskType;
import william.sundial.common.constant.TaskExecutionResult;
import william.sundial.common.constant.TaskExecutionStatus;
import william.sundial.common.dto.TaskInfoDto;
import william.sundial.core.payload.RegisterTasksCommandTransportPayload;
import william.sundial.demo.server.dao.TaskDao;
import william.sundial.demo.server.entity.TaskEntity;
import william.sundial.demo.server.service.TaskService;
import william.sundial.server.processor.RemoteTaskScheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 13:39
 * @Description:
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private RemoteTaskScheduler remoteTaskScheduler;

    @Autowired
    private TaskDao taskDao;

    @Override
    public void resolveAndCreateTasks(RegisterTasksCommandTransportPayload payload) {
        Assert.notEmpty(payload.getTasks(),"Empty Payload Data When Register Tasks!!");
        List<TaskInfoDto> tasks = payload.getTasks().stream().filter(this::needRegister).collect(Collectors.toList());
        if  (CollectionUtils.isEmpty(tasks)){
            return;
        }
        tasks.stream().map(taskInfoDto -> copyValue2TaskEntity(taskInfoDto, true)).forEach(taskEntity -> taskDao.insert(taskEntity));
        remoteTaskScheduler.addNewTasks(tasks);
    }

    @Override
    public TaskEntity getTaskByKey(String taskKey) {
        return taskDao.findByKey(taskKey);
    }

    @Override
    public void updateExecutionResult(String taskKey, TaskExecutionResult result) {
        TaskEntity taskEntity = taskDao.findByKey(taskKey);
        Assert.notNull(taskEntity,"Task Entity is Empty!! taskKey: " + taskKey);
        taskEntity.setExecutionStatus(TaskExecutionStatus.RUNNING);
        taskEntity.setLastExecutionResult(result);
        taskEntity.setLastFinishTime(new Date());
        taskDao.update(taskEntity);
    }

    private TaskEntity copyValue2TaskEntity(TaskInfoDto dto,boolean init){
        TaskEntity entity = new TaskEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setTaskType(RemoteTaskType.getByValue(dto.getTaskTypeValue()));
        if (init){
            entity.setExecutionStatus(TaskExecutionStatus.REGISTED);
        }
        return entity;
    }

    private boolean needRegister(TaskInfoDto dto){
        if (taskDao.findByKey(dto.getTaskKey()) == null){
            return true;
        }
        return false;
    }
}
