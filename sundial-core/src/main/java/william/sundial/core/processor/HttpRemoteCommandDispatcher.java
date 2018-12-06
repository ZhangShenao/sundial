package william.sundial.core.processor;

import william.felinae.json.util.JacksonUtil;
import william.sundial.core.command.DispatchCommandResult;
import william.sundial.core.command.RemoteCommand;
import william.sundial.core.payload.RemoteCommandTransportPayload;
import william.sundial.core.remote.RemoteDestination;
import william.sundial.core.remote.RemoteHostPort;
import william.sundial.core.remote.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/10/24 16:41
 * @Description:
 */
public class HttpRemoteCommandDispatcher implements RemoteCommandDispatcher {
    @Autowired
    private RestClient restClient;

    @Override
    public boolean dispatchRemoteCommand(RemoteDestination remoteDestination, RemoteCommand command) {
        if (!(remoteDestination instanceof RemoteHostPort)){
            return false;
        }
        RemoteHostPort hostPort = (RemoteHostPort)remoteDestination;
        RemoteCommandTransportPayload payloadData = command.parseTransportPayload();
        DispatchCommandResult result = restClient.queryForResponse(hostPort, JacksonUtil.writeValueAsString(payloadData), DispatchCommandResult.class, RequestMethod.POST);
        return (result == null ? false : result.isSuccess());
    }
}
