package william.sundial.demo.client;

import william.sundial.client.annotation.EnableRemoteScheduling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 16:09
 * @Description:
 */
@SpringBootApplication
@EnableRemoteScheduling
public class SundialDemoClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SundialDemoClientApplication.class,args);
    }
}
