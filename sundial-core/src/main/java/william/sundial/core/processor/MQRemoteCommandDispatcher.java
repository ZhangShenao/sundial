package william.sundial.core.processor;

import william.sundial.core.command.RemoteCommand;
import william.sundial.core.payload.RemoteCommandTransportPayload;
import william.sundial.core.remote.RemoteDestination;
import william.sundial.core.remote.RemoteMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/10/24 16:41
 * @Description:
 */
public class MQRemoteCommandDispatcher implements RemoteCommandDispatcher {
    private static final Logger logger = LoggerFactory.getLogger(MQRemoteCommandDispatcher.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public boolean dispatchRemoteCommand(RemoteDestination remoteDestination,RemoteCommand command) {
        if (!(remoteDestination instanceof RemoteMQQueue)){
            logger.error("Illegal RemoteDestination!! ");
            return false;
        }
        RemoteMQQueue remoteMQQueue = (RemoteMQQueue)remoteDestination;
        RemoteCommandTransportPayload payloadData = command.parseTransportPayload();
        jmsMessagingTemplate.convertAndSend(remoteMQQueue.getMqQueueName(),payloadData);
        return true;
    }
}
