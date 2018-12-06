package william.sundial.client.mq;

import william.sundial.client.task.resolve.RemoteTaskHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import william.sundial.common.mq.MQDestinations;
import william.sundial.core.payload.ExecuteTaskCommandTransportPayload;
import william.sundial.core.processor.RemoteTaskExecutor;
import william.sundial.core.processor.RemoteTaskMetaDataGenerator;
import william.sundial.core.task.definition.RemoteTask;
import java.util.Optional;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/26 14:48
 * @Description:Use MQ Listener to Receive Remote Tasks
 */
public class RemoteTaskMQListener{
    @Autowired
    private RemoteTaskHolder remoteTaskHolder;

    @Autowired
    private RemoteTaskExecutor remoteTaskExecutor;

    @Autowired
    private RemoteTaskMetaDataGenerator metaDataGenerator;

    @JmsListener(destination = MQDestinations.Queues.EXECUTE_TASK_QUEUE_PREFIX)
    private void receiveRemoteTask(ExecuteTaskCommandTransportPayload payload) {
        String taskKey = payload.getTaskKey();
        Optional<RemoteTask> remoteTaskOptional = remoteTaskHolder.getByKey(taskKey);
        RemoteTask remoteTask = remoteTaskOptional.orElseThrow(() -> new IllegalStateException("Could not Find Task with Key: " + taskKey));
        remoteTaskExecutor.executeRemoteTask(remoteTask);
    }
}
