package william.sundial.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 13:14
 * @Description:
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskInfoDto {
    private String taskKey;
    private String taskName;
    private String serviceName;
    private int taskTypeValue;
    private long initDelayMillis;
    private long fixedRateMillis;
    private long fixedDelayMillis;
    private String cronExpression;
}
