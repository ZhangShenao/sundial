package william.sundial.core.remote;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/13 17:25
 * @Description:
 */
public class RemoteHostPort implements RemoteDestination{
    private final String host;
    private final int port;
    private final String url;

    public RemoteHostPort(String host, int port, String url) {
        this.host = host;
        this.port = port;
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUrl() {
        return url;
    }
}
