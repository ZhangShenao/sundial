package william.sundial.client.task.resolve;

import william.sundial.core.task.definition.RemoteTask;
import java.util.Collection;
import java.util.Optional;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 11:01
 * @Description:
 */
public interface RemoteTaskHolder {
    void addTask(RemoteTask remoteTask);

    void removeTaskByKey(String taskKey);

    Collection<RemoteTask> getAllTasks();

    Optional<RemoteTask> getByKey(String taskKey);
}
