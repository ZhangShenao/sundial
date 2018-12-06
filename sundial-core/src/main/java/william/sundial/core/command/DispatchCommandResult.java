package william.sundial.core.command;


/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/21 15:55
 * @Description: Common Result for Transportation of Remote Command Transport
 */
public class DispatchCommandResult {
    private static final String SUCCESS_MESSAGE = "success";

    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public static DispatchCommandResult fail(String message){
        DispatchCommandResult result = new DispatchCommandResult();
        result.success = false;
        result.message = message;
        return result;
    }

    public static DispatchCommandResult success(){
        DispatchCommandResult result = new DispatchCommandResult();
        result.success = true;
        result.message = SUCCESS_MESSAGE;
        return result;
    }
}
