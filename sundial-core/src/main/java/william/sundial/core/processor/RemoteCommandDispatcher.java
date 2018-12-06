package william.sundial.core.processor;

import william.sundial.core.command.RemoteCommand;
import william.sundial.core.remote.RemoteDestination;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/10/24 16:20
 * @Description:
 */
public interface RemoteCommandDispatcher {
    boolean dispatchRemoteCommand(RemoteDestination remoteDestination,RemoteCommand command);
}
