package william.sundial.client.task.register;

import william.sundial.client.task.resolve.RemoteTaskHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import william.sundial.core.task.definition.RemoteTask;
import java.util.Collection;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 15:12
 * @Description: Start Register All Remote Tasks When ApplicationContext Refresh Completely
 */
@Component
public class RemoteSchedulingRegisterLauncher {
    private static final Logger logger = LoggerFactory.getLogger(RemoteSchedulingRegisterLauncher.class);

    @Autowired
    private RemoteTaskRegister remoteTaskRegister;

    @Autowired
    private RemoteTaskHolder remoteTaskHolder;

    private volatile boolean registered = false;

    @EventListener(ContextRefreshedEvent.class)
    public void startRegistAllRemoteTasks(ContextRefreshedEvent contextRefreshedEvent){
        if (registered){
            return;
        }
        Collection<RemoteTask> remoteTasks = remoteTaskHolder.getAllTasks();
        if (CollectionUtils.isEmpty(remoteTasks)){
            if (logger.isWarnEnabled()){
                logger.warn("No Remote Tasks Configured.");
            }
            return;
        }
        remoteTaskRegister.registerTasks(remoteTasks);
        registered = true;
    }
}
