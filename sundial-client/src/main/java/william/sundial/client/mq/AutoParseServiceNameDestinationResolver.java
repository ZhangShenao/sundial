package william.sundial.client.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import william.sundial.common.mq.MQDestinations;
import william.sundial.core.processor.RemoteTaskMetaDataGenerator;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/28 11:25
 * @Description:Auto Parse MQ Queue Name with Local Service Name
 */
public class AutoParseServiceNameDestinationResolver extends DynamicDestinationResolver {
    @Autowired
    private RemoteTaskMetaDataGenerator metaDataGenerator;

    @Override
    protected Queue resolveQueue(Session session, String queueName) throws JMSException {
        String targetQueueName = queueName;
        if (queueName.startsWith(MQDestinations.Queues.EXECUTE_TASK_QUEUE_PREFIX)){
            String serviceName = metaDataGenerator.fetchLocalServiceName();
            targetQueueName = MQDestinations.Queues.EXECUTE_TASK_QUEUE_PREFIX + serviceName;
        }
        return super.resolveQueue(session, targetQueueName);
    }
}
