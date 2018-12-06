package william.sundial.core.task.metadata;

import william.sundial.core.processor.TaskArgumentsProvider;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/10/24 15:05
 * @Description:
 */
@Getter
public class RemoteTaskMetaData {
    String key;
    String name;
    String serviceName;             
    Method targetMethod;
    Class<?> targetClass;
    Object invokableTarget;

    @Nullable
    Class<? extends TaskArgumentsProvider> argumentProviderType;

    RemoteTaskMetaData() {}

}
