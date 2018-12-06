package william.sundial.common.constant;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/27 16:39
 * @Description:
 */
public enum TaskExecutionResult {
    SUCCESS(1),
    FAIL(0),

    ;
    private int value;

    TaskExecutionResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
