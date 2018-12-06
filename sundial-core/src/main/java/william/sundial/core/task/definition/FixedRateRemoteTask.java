package william.sundial.core.task.definition;

import william.sundial.common.constant.RemoteTaskType;
import william.sundial.common.dto.TaskInfoDto;
import william.sundial.core.task.metadata.RemoteTaskMetaData;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 11:45
 * @Description:
 */
public class FixedRateRemoteTask extends AbstractRemoteTask{
    private long fixedRateMillis;
    private long initialDelayMillis;

    public FixedRateRemoteTask(RemoteTaskMetaData metaData, long fixedRateMillis, long initialDelayMillis) {
        super(metaData);
        this.fixedRateMillis = fixedRateMillis;
        this.initialDelayMillis = initialDelayMillis;
    }

    @Override
    public RemoteTaskType getTaskType() {
        return RemoteTaskType.FIXED_RATE_TASK;
    }

    @Override
    public String getTaskKey() {
        return getMetaData().getKey();
    }

    @Override
    public String getTaskName() {
        return getMetaData().getName();
    }

    @Override
    public void copyValue2Dto(TaskInfoDto taskInfoDto) {
        taskInfoDto.setInitDelayMillis(initialDelayMillis);
        taskInfoDto.setFixedRateMillis(fixedRateMillis);
    }
}
