package net.wyun.dianxiao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DianxiaoApplication {

	private static final Logger logger = LoggerFactory.getLogger(DianxiaoApplication.class);
    public static void main(String[] args) {
    	logger.info("start Dianxiao Application now ...");
        SpringApplication.run(DianxiaoApplication.class, args);
        logger.info("Dianxiao Application fully loaded now.");
    }
}
