package william.sundial.core.command;

import william.sundial.core.payload.RemoteCommandTransportPayload;
import org.springframework.lang.Nullable;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/10/24 16:23
 * @Description:
 */
public interface RemoteCommand<T extends RemoteCommandTransportPayload> {
    @Nullable
    T parseTransportPayload();
}
