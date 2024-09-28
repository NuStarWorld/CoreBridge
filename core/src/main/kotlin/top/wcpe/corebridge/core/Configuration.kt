package top.wcpe.corebridge.core

import org.bukkit.configuration.file.FileConfiguration

/**
 * 由 WCPE 在 2024/9/26 10:17 创建
 * <p>
 * Created by WCPE on 2024/9/26 10:17
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
class Configuration(config: FileConfiguration) {
    val debug: Boolean = config.getBoolean("debug")
}