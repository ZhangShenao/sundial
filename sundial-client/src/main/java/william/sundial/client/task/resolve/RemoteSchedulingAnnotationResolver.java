package william.sundial.client.task.resolve;

import william.sundial.client.annotation.RemoteScheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import william.sundial.core.processor.EmptyTaskArgumentsProvider;
import william.sundial.core.processor.RemoteTaskMetaDataGenerator;
import william.sundial.core.task.definition.CronRemoteTask;
import william.sundial.core.task.definition.FixedDelayRemoteTask;
import william.sundial.core.task.definition.FixedRateRemoteTask;
import william.sundial.core.task.metadata.RemoteTaskMetaData;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/1 17:59
 * @Description:Scan @RemoteTask Annotation And Process Remote Tasks
 */
public class RemoteSchedulingAnnotationResolver implements BeanPostProcessor{
    private static final Logger logger = LoggerFactory.getLogger(RemoteSchedulingAnnotationResolver.class);

    @Autowired
    private RemoteTaskHolder remoteTaskHolder;

    @Autowired
    private RemoteTaskMetaDataGenerator remoteTaskMetaDataGenerator;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);
        Map<Method, Set<RemoteScheduled>> annotatedMethods = MethodIntrospector.selectMethods(targetClass,
                (MethodIntrospector.MetadataLookup<Set<RemoteScheduled>>) method -> {
                    Set<RemoteScheduled> remoteScheduleds = AnnotatedElementUtils.findAllMergedAnnotations(method, RemoteScheduled.class);
                    return CollectionUtils.isEmpty(remoteScheduleds) ? null : remoteScheduleds;
                });

        if (CollectionUtils.isEmpty(annotatedMethods)){
            return bean;
        }

        annotatedMethods.forEach((method, taskMethods) -> taskMethods.forEach(remoteScheduled -> processRemoteTasks(remoteScheduled, method, bean)));
        if (logger.isDebugEnabled()) {
            logger.debug(annotatedMethods.size() + " @RemoteScheduled methods processed on bean '" + beanName + "': " + annotatedMethods);
        }
        return bean;
    }

    private void processRemoteTasks(RemoteScheduled remoteScheduled, Method method, Object bean){
        try {
            boolean legalArgument = (method.getParameterCount() == 0 && remoteScheduled.taskArgumentsProviderType().equals(EmptyTaskArgumentsProvider.class))
                    || (method.getParameterCount() > 0 && !remoteScheduled.taskArgumentsProviderType().equals(EmptyTaskArgumentsProvider.class));
            Assert.isTrue(legalArgument, "Method Argument and Provider Does not Match!! methodName: " + method.getName());

            long fixedDelayMillis = remoteScheduled.fixedDelayMillis();
            long fixedRateMillis = remoteScheduled.fixedRateMillis();
            String cron = remoteScheduled.cron();
            String errorMessage = "Exactly one of the 'cron', 'fixedDelayMillis', or 'fixedRateMillis' attributes is required for @RemoteScheduled";
            boolean validAnnotation = (fixedDelayMillis > 0 || fixedRateMillis > 0 || StringUtils.hasText(cron));
            Assert.isTrue(validAnnotation,errorMessage);

            boolean processedSchedule = false;

            RemoteTaskMetaData remoteTaskMetaData = remoteTaskMetaDataGenerator.generateTaskMetaData(method, bean,remoteScheduled.taskArgumentsProviderType());

            // Determine initial delay
            long initialDelayMillis = remoteScheduled.initialDelayMillis();
            if (initialDelayMillis < 0){
                initialDelayMillis = 0;
            }

            // Check cron expression
            if (StringUtils.hasText(cron)) {
                String zone = remoteScheduled.zone();
                processedSchedule = true;
                TimeZone timeZone;
                if (StringUtils.hasText(zone)) {
                    timeZone = StringUtils.parseTimeZoneString(zone);
                }
                else {
                    timeZone = TimeZone.getDefault();
                }

                remoteTaskHolder.addTask(new CronRemoteTask(remoteTaskMetaData,new CronTrigger(cron, timeZone)));
            }

            // Check fixed delay
            else if (fixedDelayMillis > 0) {
                Assert.isTrue(!processedSchedule, errorMessage);
                processedSchedule = true;
                remoteTaskHolder.addTask(new FixedDelayRemoteTask(remoteTaskMetaData,fixedDelayMillis,initialDelayMillis));
            }


            // Check fixed rate
            else if (fixedRateMillis >= 0) {
                Assert.isTrue(!processedSchedule, errorMessage);
                processedSchedule = true;
                remoteTaskHolder.addTask(new FixedRateRemoteTask(remoteTaskMetaData,fixedRateMillis,initialDelayMillis));
            }

            // Check whether we had any attribute set
            Assert.isTrue(processedSchedule, errorMessage);

        }
        catch (IllegalArgumentException ex) {
            throw new IllegalStateException("Encountered invalid @RemoteScheduled method '" + method.getName() + "': " + ex.getMessage());
        }
    }

}
