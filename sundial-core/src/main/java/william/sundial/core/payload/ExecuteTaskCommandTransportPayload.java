package william.sundial.core.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/21 15:47
 * @Description:
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecuteTaskCommandTransportPayload implements RemoteCommandTransportPayload {
    private String taskKey;

    public static ExecuteTaskCommandTransportPayload valueOf(String taskKey){
        ExecuteTaskCommandTransportPayload payload = new ExecuteTaskCommandTransportPayload();
        payload.taskKey = taskKey;
        return payload;
    }
}
