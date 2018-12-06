package william.sundial.demo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 13:26
 * @Description:
 */
@SpringBootApplication
@EnableScheduling
public class SundialDemoServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SundialDemoServerApplication.class,args);
    }
}
