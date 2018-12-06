package william.sundial.core.utils;

import william.sundial.common.dto.TaskInfoDto;
import william.sundial.core.task.definition.RemoteTask;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 13:18
 * @Description:Convert Task Data between Remote Task Instances and Task DTOs
 */
public class TaskDataConverter {
    public static final TaskInfoDto taskInstance2Dto(RemoteTask remoteTask){
        TaskInfoDto dto = new TaskInfoDto();
        dto.setTaskKey(remoteTask.getTaskKey());
        dto.setTaskName(remoteTask.getTaskName());
        dto.setServiceName(remoteTask.getMetaData().getServiceName());
        dto.setTaskTypeValue(remoteTask.getTaskType().getValue());
        remoteTask.copyValue2Dto(dto);
        return dto;
    }
}
