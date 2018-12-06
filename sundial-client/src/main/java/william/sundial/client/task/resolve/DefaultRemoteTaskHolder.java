package william.sundial.client.task.resolve;

import william.sundial.core.task.definition.RemoteTask;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 11:03
 * @Description:Default Implementation of RemoteTaskHolder
 */
public class DefaultRemoteTaskHolder implements RemoteTaskHolder{
    private Map<String,RemoteTask> remoteTasks = new ConcurrentHashMap<>();

    @Override
    public void addTask(RemoteTask remoteTask) {
        remoteTasks.put(remoteTask.getTaskKey(),remoteTask);
    }

    @Override
    public void removeTaskByKey(String taskKey) {
        remoteTasks.remove(taskKey);
    }

    @Override
    public Collection<RemoteTask> getAllTasks() {
        return Collections.unmodifiableCollection(remoteTasks.values());
    }

    @Override
    public Optional<RemoteTask> getByKey(String taskKey) {
        return Optional.of(remoteTasks.get(taskKey));
    }
}
