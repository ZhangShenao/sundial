package william.sundial.demo.client.service;

import william.sundial.client.annotation.RemoteScheduled;
import william.sundial.demo.client.provider.DemoTaskArgumentsProvider;
import org.springframework.stereotype.Service;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 16:10
 * @Description:
 */
@Service
public class DemoClientService {
    @RemoteScheduled(fixedRateMillis = 1000L * 60,initialDelayMillis = 10000L)
    public void execAtFixedRate(){
        System.err.println("Execute At Fixed Rate: " + System.currentTimeMillis());
    }

    @RemoteScheduled(fixedDelayMillis = 1000L * 60,initialDelayMillis = 10000L,taskArgumentsProviderType = DemoTaskArgumentsProvider.class)
    public void execWithFixedDelay(int a,int b){
        System.err.println("Execute With Fixed Delay: " + System.currentTimeMillis() + ",params: " + a + ", " + b);
    }
}
