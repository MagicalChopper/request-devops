package com.yudianbank.request.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestExecutor {
    private static final Logger logger = LoggerFactory.getLogger(RequestExecutor.class);

    // ---------------------- start + stop ----------------------
    public void start() {
        logger.info("RequestExecutor is starting");
    }
    public void destroy(){
        logger.info("RequestExecutor destoryed");
    }
}
