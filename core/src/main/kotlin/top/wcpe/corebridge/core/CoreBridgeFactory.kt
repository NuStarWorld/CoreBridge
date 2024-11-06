package top.wcpe.corebridge.core

import org.bukkit.Bukkit
import top.wcpe.corebridge.api.CoreBridgeApi
import top.wcpe.corebridge.impl.dragoncore.DragonCoreCoreBridgeApiImpl
import top.wcpe.corebridge.impl.easycore.EasyCoreCoreBridgeApiImpl
import top.wcpe.corebridge.impl.germplugin.GermPluginCoreBridgeApiImpl

/**
 * 由 WCPE 在 2024/9/26 12:08 创建
 * <p>
 * Created by WCPE on 2024/9/26 12:08
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object CoreBridgeFactory {
    /**
     * 创建一个 CoreBridgeManager 对象，用于管理 CoreBridgeApi 实例
     */
    @JvmStatic
    fun createCoreBridgeManager(): CoreBridgeManager {
        val apis = mutableListOf<CoreBridgeApi>()
        val server = Bukkit.getServer()
        val dragonCorePlugin = server.pluginManager.getPlugin("DragonCore")
        val easyCorePlugin = server.pluginManager.getPlugin("EasyCore")
        val germPlugin = server.pluginManager.getPlugin("GermPlugin")

        if (dragonCorePlugin != null) {
            apis.add(DragonCoreCoreBridgeApiImpl.init(CoreBridge.instance, dragonCorePlugin))
        }
        if (easyCorePlugin != null) {
            apis.add(EasyCoreCoreBridgeApiImpl.init(CoreBridge.instance))
        }
        if (germPlugin != null) {
            apis.add(GermPluginCoreBridgeApiImpl.init(CoreBridge.instance, germPlugin))
        }
        if (apis.isEmpty()) {
            throw RuntimeException("未找到任何核心插件, 请至少安装 DragonCore | EasyCore | GermPlugin 中的一个!")
        }
        return CoreBridgeManager(apis)
    }
}