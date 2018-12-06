package william.sundial.core.task.definition;

import william.sundial.common.constant.RemoteTaskType;
import william.sundial.common.dto.TaskInfoDto;
import william.sundial.core.task.metadata.RemoteTaskMetaData;
import org.springframework.scheduling.support.CronTrigger;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 11:45
 * @Description:
 */
public class CronRemoteTask extends AbstractRemoteTask{
    private CronTrigger cronTrigger;

    public CronRemoteTask(RemoteTaskMetaData metaData, CronTrigger cronTrigger) {
        super(metaData);
        this.cronTrigger = cronTrigger;
    }

    @Override
    public RemoteTaskType getTaskType() {
        return RemoteTaskType.CRON_TASK;
    }

    @Override
    public void copyValue2Dto(TaskInfoDto taskInfoDto) {
        taskInfoDto.setCronExpression(cronTrigger.getExpression());
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
