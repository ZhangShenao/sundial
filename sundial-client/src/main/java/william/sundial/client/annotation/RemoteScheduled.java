package william.sundial.client.annotation;

import william.sundial.core.processor.EmptyTaskArgumentsProvider;
import william.sundial.core.processor.TaskArgumentsProvider;
import java.lang.annotation.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/1 17:53
 * @Description: Mark A Class Or Method As Remote Task
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RemoteScheduled {
    String cron() default "";

    long fixedDelayMillis() default 0;

    long fixedRateMillis() default 0;

    long initialDelayMillis() default 0;

    String zone() default "";

    Class<? extends TaskArgumentsProvider> taskArgumentsProviderType() default EmptyTaskArgumentsProvider.class;
}
