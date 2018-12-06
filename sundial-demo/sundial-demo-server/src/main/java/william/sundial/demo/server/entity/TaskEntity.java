package william.sundial.demo.server.entity;

import william.mybatis.sql.generator.IEntity;
import william.sundial.common.constant.RemoteTaskType;
import william.sundial.common.constant.TaskExecutionResult;
import william.sundial.common.constant.TaskExecutionStatus;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 14:07
 * @Description:
 */
@Getter
@Setter
public class TaskEntity implements Serializable, IEntity {
    public static final String TABLE_NAME = "sundial_task";
    public static final String KEY_PROP = "id";

    private long id;
    private String taskName;
    private String taskKey;
    private String serviceName;
    private RemoteTaskType taskType;
    private TaskExecutionStatus executionStatus;
    private TaskExecutionResult lastExecutionResult;
    private Date lastFinishTime;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String tableName() {
        return TABLE_NAME;
    }

    @Override
    public String keyProperty() {
        return KEY_PROP;
    }
}
