package william.sundial.core.task.definition;

import william.sundial.common.constant.RemoteTaskType;
import william.sundial.common.dto.TaskInfoDto;
import william.sundial.core.task.metadata.RemoteTaskMetaData;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 11:40
 * @Description:
 */
public class FixedDelayRemoteTask extends AbstractRemoteTask{
    private long fixedDelayMillis;
    private long initialDelayMillis;

    public FixedDelayRemoteTask(RemoteTaskMetaData metaData, long fixedDelayMillis, long initialDelayMillis) {
        super(metaData);
        this.fixedDelayMillis = fixedDelayMillis;
        this.initialDelayMillis = initialDelayMillis;
    }

    @Override
    public RemoteTaskType getTaskType() {
        return RemoteTaskType.FIXED_DELAY_TASK;
    }

    @Override
    public void copyValue2Dto(TaskInfoDto taskInfoDto) {
        taskInfoDto.setInitDelayMillis(initialDelayMillis);
        taskInfoDto.setFixedDelayMillis(fixedDelayMillis);
    }

    @Override
    public String getTaskKey() {
        return getMetaData().getKey();
    }

    @Override
    public String getTaskName() {
        return getMetaData().getName();
    }
}
