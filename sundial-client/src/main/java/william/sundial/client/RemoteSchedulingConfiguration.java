package william.sundial.client;

import william.sundial.client.mq.AutoParseServiceNameDestinationResolver;
import william.sundial.client.mq.RemoteTaskMQListener;
import william.sundial.client.processor.PooledAsyncRemoteTaskExecutor;
import william.sundial.client.task.register.HttpRemoteTaskRegister;
import william.sundial.client.task.register.RemoteTaskRegister;
import william.sundial.client.task.resolve.DefaultRemoteTaskHolder;
import william.sundial.client.task.resolve.RemoteSchedulingAnnotationResolver;
import william.sundial.client.task.resolve.RemoteTaskHolder;
import william.sundial.client.utils.TaskManagementUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.web.client.RestTemplate;
import william.sundial.core.processor.*;
import william.sundial.core.remote.RestClient;

import java.nio.charset.StandardCharsets;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/1 17:57
 * @Description:Central Configuration of Task,Register All Required Components
 */
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ComponentScan
public class RemoteSchedulingConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public RestTemplate encodedRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Bean
    public RestClient restClient(){
        return new RestClient();
    }

    @Bean
    public HttpRemoteCommandDispatcher httpRemoteCommandDispatcher(){
        return new HttpRemoteCommandDispatcher();
    }

    @Bean
    public MQRemoteCommandDispatcher mqRemoteCommandDispatcher(){
        return new MQRemoteCommandDispatcher();
    }

    @Bean
    public RemoteTaskRegister httpRemoteTaskRegister(){
        return new HttpRemoteTaskRegister();
    }

    @Bean
    public RemoteTaskHolder defaultRemoteTaskHolder(){
        return new DefaultRemoteTaskHolder();
    }

    @Bean
    public RemoteTaskMetaDataGenerator defaultRemoteTaskMetaDataGenerator(){
        return new DefaultRemoteTaskMetaDataGenerator();
    }

    @Bean(name = TaskManagementUtils.TASK_ANNOTATION_BEAN_POST_PROCESSOR_NAME)
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public RemoteSchedulingAnnotationResolver taskAnnotationBeanPostProcessor() {
        return new RemoteSchedulingAnnotationResolver();
    }

    @Bean
    public RemoteTaskMQListener remoteTaskMQListener(){
        return new RemoteTaskMQListener();
    }

    @Bean
    public RemoteTaskExecutor remoteTaskExecutor(){
        return new PooledAsyncRemoteTaskExecutor();
    }

    @Bean
    public DestinationResolver destinationResolver(){
        return new AutoParseServiceNameDestinationResolver();
    }

}
