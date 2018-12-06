package william.sundial.core.remote;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/13 17:25
 * @Description:
 */
public class RemoteMQQueue implements RemoteDestination{
    private final String mqQueueName;

    public RemoteMQQueue(String mqQueueName) {
        this.mqQueueName = mqQueueName;
    }

    public String getMqQueueName() {
        return mqQueueName;
    }
}
