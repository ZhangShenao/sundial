package william.sundial.core.command;

import william.sundial.core.payload.ExecuteTaskCommandTransportPayload;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/10/24 16:30
 * @Description:
 */
@Getter
@Setter
public class ExecuteTaskCommand implements RemoteCommand<ExecuteTaskCommandTransportPayload> {
    private String taskKey;

    @Override
    public ExecuteTaskCommandTransportPayload parseTransportPayload() {
        return ExecuteTaskCommandTransportPayload.valueOf(taskKey);
    }
}
