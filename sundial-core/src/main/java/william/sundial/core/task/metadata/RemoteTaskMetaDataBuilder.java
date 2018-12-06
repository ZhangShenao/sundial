package william.sundial.core.task.metadata;

import william.sundial.core.processor.TaskArgumentsProvider;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 14:29
 * @Description:
 */
public class RemoteTaskMetaDataBuilder {
    private RemoteTaskMetaData metaData = new RemoteTaskMetaData();

    public RemoteTaskMetaDataBuilder taskKey(String taskKey){
        metaData.key = taskKey;
        return this;
    }

    public RemoteTaskMetaDataBuilder taskName(String taskName){
        metaData.name = taskName;
        return this;
    }

    public RemoteTaskMetaDataBuilder serviceName(String serviceName){
        metaData.serviceName = serviceName;
        return this;
    }

    public RemoteTaskMetaDataBuilder method(Method method){
        metaData.targetMethod = method;
        return this;
    }

    public RemoteTaskMetaDataBuilder invokableTarget(Object invokableTarget){
        metaData.invokableTarget = invokableTarget;
        return this;
    }

    public RemoteTaskMetaDataBuilder targetClass(Class<?> targetClass){
        metaData.targetClass = targetClass;
        return this;
    }

    public RemoteTaskMetaDataBuilder argumentProviderType(Class<? extends TaskArgumentsProvider> argumentProviderType){
        metaData.argumentProviderType = argumentProviderType;
        return this;
    }


    public RemoteTaskMetaData build(){
        checkCommonParams();
        return metaData;
    }

    private void checkCommonParams(){
        boolean legalParam = (StringUtils.hasText(metaData.key) &&
                StringUtils.hasText(metaData.name) &&
                StringUtils.hasText(metaData.serviceName) &&
                metaData.targetMethod != null &&
                metaData.invokableTarget != null);

        Assert.isTrue(legalParam,"RemoteTaskMetaData Param Error!!");
    }


}
