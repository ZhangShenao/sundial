package william.sundial.core.processor;

import william.sundial.core.task.metadata.RemoteTaskMetaData;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 13:31
 * @Description:Generate Required Data When Creating RemoteTask
 */
public interface RemoteTaskMetaDataGenerator {
    String generateTaskKey(Method method, Object bean);

    String generateTaskName(Method method, Object bean);

    RemoteTaskMetaData generateTaskMetaData(Method method, Object bean,@Nullable Class<? extends TaskArgumentsProvider> argumentProviderType);

    String fetchLocalServiceName();

}
