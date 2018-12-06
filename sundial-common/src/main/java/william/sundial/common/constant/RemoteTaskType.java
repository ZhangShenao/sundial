package william.sundial.common.constant;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 11:42
 * @Description:
 */
public enum RemoteTaskType {
    FIXED_DELAY_TASK(1),
    FIXED_RATE_TASK(2),
    CRON_TASK(3),

    NULL(-1),
    ;
    private int value;

    RemoteTaskType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RemoteTaskType getByValue(int value){
        for (RemoteTaskType taskType : values()){
            if (taskType.value == value){
                return taskType;
            }
        }
        return NULL;
    }
}
