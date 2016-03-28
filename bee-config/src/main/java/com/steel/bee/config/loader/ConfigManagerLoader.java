package com.steel.bee.config.loader;

import com.steel.bee.common.extension.ExtensionLoader;
import com.steel.bee.common.log.LoggerLoader;
import com.steel.bee.config.ConfigManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by gang.qin on 2015/12/31.
 */
public class ConfigManagerLoader {
    private static ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class); // spi机制获取
    private static final Logger logger = LoggerLoader.getLogger(ConfigManager.class);

    static {
        if (configManager == null) {
            configManager = null;
        }
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

}
