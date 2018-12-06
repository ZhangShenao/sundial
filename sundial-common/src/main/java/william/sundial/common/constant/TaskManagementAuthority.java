package william.sundial.common.constant;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/10/23 15:36
 * @Description:
 */
public enum TaskManagementAuthority {
    NULL(0),                 //No Authority
    READ(1 << 0),            //Can Monitor Tasks
    WRITE(1 << 1),           //Can Create And Delete Tasks
    EXECUTE(1 << 2),         //Can Execute Tasks Manually
    ;

    private int authorityValue;

    TaskManagementAuthority(int authorityValue) {
        this.authorityValue = authorityValue;
    }

    public int getAuthorityValue() {
        return authorityValue;
    }
}
