package william.sundial.demo.server.controller;

import william.sundial.common.constant.RemoteURLS;
import william.sundial.core.command.DispatchCommandResult;
import william.sundial.core.payload.RegisterTasksCommandTransportPayload;
import william.sundial.demo.server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/21 16:44
 * @Description:REST Controller for Internal Services such as Register Tasks
 */
@RestController
public class SundialServerInternalController {
    @Autowired
    private TaskService taskService;

    @PostMapping(RemoteURLS.REGISTER_TASKS_URL)
    public DispatchCommandResult registerRemoteTasks(@RequestBody RegisterTasksCommandTransportPayload payload){
        Assert.notEmpty(payload.getTasks(),"Remote Tasks Must bot be Empty!!");
        taskService.resolveAndCreateTasks(payload);
        return DispatchCommandResult.success();
    }
}
