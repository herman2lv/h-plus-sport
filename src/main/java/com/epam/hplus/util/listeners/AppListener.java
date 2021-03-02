package com.epam.hplus.util.listeners;

import com.epam.hplus.model.pool.ConnectionPool;
import com.epam.hplus.util.resources.LogManager;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class AppListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info(LogManager.getMessage("log.appLoaded"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.INSTANCE.destroyPool();
        LOGGER.info(LogManager.getMessage("log.poolDestroyed"));
    }
}
