package william.sundial.core.processor;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/28 14:14
 * @Description:
 */
public class EmptyTaskArgumentsProvider implements TaskArgumentsProvider{
    @Override
    public Object[] provideTaskArguments() {
        return new Object[0];
    }
}
