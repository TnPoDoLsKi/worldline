package org.example.config;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerConfig {
    public synchronized static Logger getLogger(Class<?> classname) {
        Logger logger = Logger.getLogger(classname.getName());
        logger.setLevel(Level.parse(Properties.LOG_LEVEL));
        return logger;
    }
}
