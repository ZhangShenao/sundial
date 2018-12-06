package william.sundial.common.mq;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/21 16:10
 * @Description:Manage All Destinations of MQ
 */
public interface MQDestinations {
    interface Queues{
        String EXECUTE_TASK_QUEUE_PREFIX = "ExecuteRemoteTask.";
        String RECORD_TASK_RESULT_QUEUE = "RegisterTaskResult";
    }

}
