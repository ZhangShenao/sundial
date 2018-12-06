package william.sundial.client.task.register;

import william.sundial.core.task.definition.RemoteTask;
import java.util.Collection;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/10/24 15:50
 * @Description:
 */
public interface RemoteTaskRegister {
    boolean registerTasks(Collection<RemoteTask> remoteTasks);
}
