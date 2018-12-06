package william.sundial.core.processor;

import william.sundial.core.task.metadata.RemoteTaskMetaData;
import william.sundial.core.task.metadata.RemoteTaskMetaDataBuilder;
import lombok.Getter;
import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 13:32
 * @Description:Default Implementation of RemoteTaskMetaDataGenerator
 */
@Getter
public class DefaultRemoteTaskMetaDataGenerator implements RemoteTaskMetaDataGenerator{
    private static final String DELIMITER = "-";
    private static final String DEFAULT_LOCAL_SERVICE_NAME = "Default";

    private String localServiceName;

    @PostConstruct
    private void init(){
        //TODO Fetch Local Service-Name
        /*if (ZaeEnv.isProductionEnv()){
            localServiceName = System.getenv(EnvPropertyNames.LOCAL_SERVICE_NAME);
            Assert.isTrue(StringUtils.hasText(localServiceName),"Fetch Local Service-Name Error!!");
        }
        else {
            localServiceName = DEFAULT_LOCAL_SERVICE_NAME;
        }*/

    }

    @Override
    public String generateTaskKey(Method method, Object bean) {
        return fetchLocalServiceName() + DELIMITER + bean.getClass().getCanonicalName() + DELIMITER + method.getName();
    }

    @Override
    public String generateTaskName(Method method, Object bean) {
        return method.getName();
    }

    @Override
    public RemoteTaskMetaData generateTaskMetaData(Method method, Object bean,Class<? extends TaskArgumentsProvider> argumentProviderType) {
        String taskKey = generateTaskKey(method, bean);
        String taskName = generateTaskName(method, bean);
        return new RemoteTaskMetaDataBuilder()
                .taskKey(taskKey)
                .taskName(taskName)
                .serviceName(getLocalServiceName())
                .method(method)
                .invokableTarget(bean)
                .targetClass(bean.getClass())
                .argumentProviderType(argumentProviderType)
                .build();
    }

    @Override
    public String fetchLocalServiceName() {
        return localServiceName;
    }


}
