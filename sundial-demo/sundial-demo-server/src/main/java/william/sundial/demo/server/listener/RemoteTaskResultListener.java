package william.sundial.demo.server.listener;

import william.sundial.common.constant.TaskExecutionResult;
import william.sundial.common.mq.MQDestinations;
import william.sundial.core.payload.RecordTaskResultCommandTransportPayload;
import william.sundial.demo.server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/27 13:10
 * @Description:Listen Message of Remote Task Execution Result
 */
@Component
public class RemoteTaskResultListener {
    @Autowired
    private TaskService taskService;

    @JmsListener(destination = MQDestinations.Queues.RECORD_TASK_RESULT_QUEUE)
    private void recordTaskResult(RecordTaskResultCommandTransportPayload payload){
        Assert.notNull(payload.getTaskInfoDto(),"Task Info Data is Null When Record Task Result!!");
        Assert.notNull(payload.getTaskResultDto(),"Task Result Data is Null When Record Task Result!!");
        taskService.updateExecutionResult(payload.getTaskInfoDto().getTaskKey(),payload.getTaskResultDto().isSuccess() ? TaskExecutionResult.SUCCESS : TaskExecutionResult.FAIL);
    }
}
