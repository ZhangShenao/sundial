package william.sundial.core.processor;

import william.sundial.core.task.definition.RemoteTask;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/26 15:01
 * @Description:
 */
public interface RemoteTaskExecutor {
    void executeRemoteTask(RemoteTask remoteTask);
}
