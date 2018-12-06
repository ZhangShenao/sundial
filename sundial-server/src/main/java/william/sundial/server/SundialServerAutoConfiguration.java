package william.sundial.server;

import william.sundial.core.processor.MQRemoteCommandDispatcher;
import william.sundial.core.processor.RemoteCommandDispatcher;
import william.sundial.server.concurrent.SundialThreadErrorHandler;
import william.sundial.server.processor.RemoteTaskScheduler;
import william.sundial.server.processor.impl.PooledRemoteTaskScheduler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 15:27
 * @Description:Core Configuration of sundial-server
 */
@Configuration
public class SundialServerAutoConfiguration {
    @Bean
    public RemoteCommandDispatcher mqRemoteCommandDispatcher(){
        return new MQRemoteCommandDispatcher();
    }

    @Bean
    public SundialThreadErrorHandler sundialThreadErrorHandler(){
        return new SundialThreadErrorHandler();
    }

    @Bean
    @ConditionalOnMissingBean(ThreadPoolTaskExecutor.class)
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(SundialThreadErrorHandler sundialThreadErrorHandler){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(Runtime.getRuntime().availableProcessors() * 2);
        scheduler.setRejectedExecutionHandler(sundialThreadErrorHandler);
        scheduler.setErrorHandler(sundialThreadErrorHandler);
        return scheduler;
    }

    @Bean
    public RemoteTaskScheduler remoteTaskScheduler(){
        return new PooledRemoteTaskScheduler();
    }
}
