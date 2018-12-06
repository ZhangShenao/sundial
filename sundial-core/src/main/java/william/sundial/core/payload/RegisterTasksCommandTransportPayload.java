package william.sundial.core.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import william.sundial.common.dto.TaskInfoDto;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/21 15:44
 * @Description:
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterTasksCommandTransportPayload implements RemoteCommandTransportPayload {
    private List<TaskInfoDto> tasks;

    public static RegisterTasksCommandTransportPayload valueOf(List<TaskInfoDto> tasks){
        RegisterTasksCommandTransportPayload payload = new RegisterTasksCommandTransportPayload();
        payload.setTasks(tasks);
        return payload;
    }
}
