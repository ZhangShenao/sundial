package william.sundial.common.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/26 15:08
 * @Description:
 */
@Getter
@Setter
public class TaskResultDto {
    private boolean success;

    @Nullable
    private Object payload;

    private String message;

    public static TaskResultDto success(Object payload){
        TaskResultDto dto = new TaskResultDto();
        dto.success = true;
        dto.message = "success";
        dto.payload = payload;
        return dto;
    }

    public static TaskResultDto fail(Throwable throwable){
        TaskResultDto dto = new TaskResultDto();
        dto.success = false;
        dto.message = throwable.getMessage();
        return dto;
    }

    public static TaskResultDto fail(String message){
        TaskResultDto dto = new TaskResultDto();
        dto.success = false;
        dto.message = message;
        return dto;
    }
}
