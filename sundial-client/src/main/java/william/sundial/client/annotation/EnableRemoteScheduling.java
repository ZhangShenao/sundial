package william.sundial.client.annotation;

import william.sundial.client.RemoteSchedulingConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/1 17:56
 * @Description:Open Remote Task Function
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RemoteSchedulingConfiguration.class)
@Documented
public @interface EnableRemoteScheduling {
}
