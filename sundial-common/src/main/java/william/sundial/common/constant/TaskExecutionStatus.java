package william.sundial.common.constant;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/10/24 17:12
 * @Description:
 */
public enum TaskExecutionStatus {
    REGISTED(0),
    RUNNING(1),
    COMPLETED(2),
    CANCELED(3),
    ;

    TaskExecutionStatus(int value) {
        this.value = value;
    }

    private int value;
}
