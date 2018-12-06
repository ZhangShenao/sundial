package william.sundial.core.task.definition;

import william.sundial.common.constant.RemoteTaskType;
import william.sundial.common.dto.TaskInfoDto;
import william.sundial.core.task.metadata.RemoteTaskMetaData;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 10:47
 * @Description:
 */
public interface RemoteTask {
    String getTaskKey();

    String getTaskName();

    RemoteTaskType getTaskType();

    RemoteTaskMetaData getMetaData();

    void copyValue2Dto(TaskInfoDto taskInfoDto);
}
