package william.sundial.core.task.definition;

import william.sundial.common.constant.RemoteTaskType;
import william.sundial.core.task.metadata.RemoteTaskMetaData;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 10:43
 * @Description:
 */
public abstract class AbstractRemoteTask implements RemoteTask{
    private RemoteTaskMetaData metaData;

    public AbstractRemoteTask(RemoteTaskMetaData metaData) {
        this.metaData = metaData;
    }

    public abstract RemoteTaskType getTaskType();

    @Override
    public RemoteTaskMetaData getMetaData() {
        return this.metaData;
    }
}
