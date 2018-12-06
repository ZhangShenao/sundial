package william.sundial.demo.server.service;

import william.sundial.common.constant.TaskExecutionResult;
import william.sundial.core.payload.RegisterTasksCommandTransportPayload;
import william.sundial.demo.server.entity.TaskEntity;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 13:32
 * @Description:
 */
public interface TaskService {
    void resolveAndCreateTasks(RegisterTasksCommandTransportPayload payload);

    TaskEntity getTaskByKey(String taskKey);

    void updateExecutionResult(String taskKey, TaskExecutionResult result);
}
