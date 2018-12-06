package william.sundial.demo.client.provider;

import william.sundial.core.processor.TaskArgumentsProvider;
import org.springframework.stereotype.Component;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/28 14:19
 * @Description:
 */
@Component
public class DemoTaskArgumentsProvider implements TaskArgumentsProvider {
    @Override
    public Object[] provideTaskArguments() {
        Object[] args = new Object[2];
        args[0] = 1;
        args[1] = 2;
        return args;
    }
}
