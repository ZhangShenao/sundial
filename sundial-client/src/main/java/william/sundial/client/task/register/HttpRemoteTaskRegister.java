package william.sundial.client.task.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import william.sundial.common.constant.RemoteURLS;
import william.sundial.core.command.RegisterTasksCommand;
import william.sundial.core.processor.HttpRemoteCommandDispatcher;
import william.sundial.core.remote.RemoteDestination;
import william.sundial.core.remote.RemoteHostPort;
import william.sundial.core.task.definition.RemoteTask;
import java.util.Collection;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/5 11:03
 * @Description:Default Implementation of RemoteTaskRegister Using Http
 */
public class HttpRemoteTaskRegister implements RemoteTaskRegister{
    private static final Logger logger = LoggerFactory.getLogger(RemoteTaskRegister.class);

    @Autowired
    private HttpRemoteCommandDispatcher remoteCommandDispatcher;

    @Override
    public boolean registerTasks(Collection<RemoteTask> remoteTasks) {
        try {
            /*HttpHost taskCenter = diplomat.getRandom(TaskCenterConstants.TASK_CENTER_SERVICE_NAME);

            //Just For Test
            if (taskCenter == null && ZaeEnv.isDevelopEnv()){
                taskCenter = new HttpHost("127.0.0.1",8080);
            }
            Assert.notNull(taskCenter,"Can't Fetch Route For Task-Center");*/

            //TODO Fetch Task-Center Host-Port
            RemoteDestination remoteDestination = new RemoteHostPort("127.0.0.1", 8080, RemoteURLS.REGISTER_TASKS_URL);
            RegisterTasksCommand registerTasksCommand = new RegisterTasksCommand(remoteTasks);
            return remoteCommandDispatcher.dispatchRemoteCommand(remoteDestination,registerTasksCommand);

        } catch (Exception e) {
            logger.error("Fetch Route of Task-Center Error!! ",e);
            return false;
        }
    }
}
