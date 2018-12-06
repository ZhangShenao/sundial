package william.sundial.core.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import william.felinae.json.util.JacksonUtil;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/14 15:48
 * @Description:
 */
public class RestClient {
    private Logger logger = LoggerFactory.getLogger(RestClient.class);

    @Autowired
    @Qualifier("encodedRestTemplate")
    private RestTemplate restTemplate;

    private static final String URL_PREFIX = "http";
    private static final String URL_FORMAT = "http://%s";

    public <T> T queryForResponse(RemoteHostPort hostPort, String requestJson, Class<T> responseClass, RequestMethod requestMethod){
        String url = formatURL(hostPort);
        return  queryForResponse(url,requestJson,responseClass,requestMethod);
    }

    public <T> T queryForResponse(String url, String requestJson, Class<T> responseClass, RequestMethod requestMethod){
        ResponseEntity<String> responseEntity = null;
        switch (requestMethod){
            case GET: responseEntity = restTemplate.getForEntity(url,String.class); break;
            case POST: {
                HttpHeaders headers = new HttpHeaders();
                MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
                headers.setContentType(type);
                HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);
                responseEntity =  restTemplate.postForEntity(url,entity,String.class);
            } break;
        }

        if (responseEntity == null){
            logger.error("Get Rest Response Error !! URL: {},responseClass: {},requestMethod: {}",
                    url,responseClass.getName(),requestMethod.name());
            return null;
        }

        String respBody = responseEntity.getBody();
        try {
            return JacksonUtil.readValue(respBody,responseClass);
        } catch (Exception e) {
            logger.error("Get Rest Response Error !! URL: {},responseClass: {},requestMethod: {}",
                    url,responseClass.getName(),requestMethod.name());
            throw new RuntimeException(e);
        }
    }

    private String formatURL(RemoteHostPort hostPort){
        String url = hostPort.getHost() + ":" + hostPort.getPort() + hostPort.getUrl();
        if (!url.startsWith(URL_PREFIX)){
            url = String.format(URL_FORMAT,url);
        }
        return url;
    }
}
