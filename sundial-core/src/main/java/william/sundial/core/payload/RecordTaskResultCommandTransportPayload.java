package william.sundial.core.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import william.sundial.common.dto.TaskInfoDto;
import william.sundial.common.dto.TaskResultDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/21 15:48
 * @Description:
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordTaskResultCommandTransportPayload implements RemoteCommandTransportPayload {
    private TaskInfoDto taskInfoDto;
    private TaskResultDto taskResultDto;

    public static RecordTaskResultCommandTransportPayload valueOf(TaskInfoDto taskInfoDto,TaskResultDto taskResultDto){
        RecordTaskResultCommandTransportPayload payload = new RecordTaskResultCommandTransportPayload();
        payload.setTaskInfoDto(taskInfoDto);
        payload.setTaskResultDto(taskResultDto);
        return payload;
    }
}
