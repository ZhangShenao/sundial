package william.sundial.server.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 15:02
 * @Description:Handle Rejected Threads and Exceptions
 */
public class SundialThreadErrorHandler implements RejectedExecutionHandler,ErrorHandler{
    private static final Logger logger = LoggerFactory.getLogger(SundialThreadErrorHandler.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        logger.error("Task Rejected!! ");
    }

    @Override
    public void handleError(Throwable t) {
        logger.error("Task Error!! ",t);
    }
}
