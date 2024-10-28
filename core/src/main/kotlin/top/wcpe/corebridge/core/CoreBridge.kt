package top.wcpe.corebridge.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import top.wcpe.corebridge.api.CoreBridgeApi
import top.wcpe.corebridge.api.CoreBridgeInstance
import top.wcpe.corebridge.api.event.CustomPacketEvent
import top.wcpe.corebridge.core.commands.CoreBridgeCommand
import top.wcpe.corebridge.core.commands.CoreBridgeGuiCommand
import top.wcpe.corebridge.core.commands.CoreBridgePacketCommand
import top.wcpe.corebridge.core.commands.CoreBridgePlaceholderCommand
import top.wcpe.corebridge.core.packet.PacketManager
import top.wcpe.wcpelib.common.command.v2.CommandManager
import java.io.File
import java.util.function.Consumer
import java.util.logging.Logger

/**
 * 由 WCPE 在 2024/9/25 17:55 创建
 * <p>
 * Created by WCPE on 2024/9/25 17:55
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
class CoreBridge : JavaPlugin(), CoreBridgeInstance {

    companion object {
        @JvmStatic
        lateinit var instance: CoreBridge
            private set

        @JvmStatic
        lateinit var api: CoreBridgeApi
            private set

        @JvmStatic
        val pluginScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    }

    var configuration: Configuration? = null
        private set

    private val messageFile = File(dataFolder, "message.yml")
    var messageConfig: FileConfiguration? = null
        private set

    override fun saveDefaultConfig() {
        super.saveDefaultConfig()
        if (!messageFile.exists()) {
            saveResource("message.yml", false)
        }
    }

    fun reloadOtherConfig() {
        reloadConfig()
        configuration = Configuration(config)
        messageConfig = YamlConfiguration.loadConfiguration(messageFile)
    }

    override fun onLoad() {
        instance = this
        saveDefaultConfig()
        reloadOtherConfig()
    }

    override fun onEnable() {
        val coreBridgeManager = CoreBridgeFactory.createCoreBridgeManager()
        top.wcpe.corebridge.api.CoreBridge.init(this, coreBridgeManager)
        api = coreBridgeManager
        CommandManager.registerCommand(CoreBridgeCommand::class.java, this)
        CommandManager.registerCommand(CoreBridgeGuiCommand::class.java, this)
        CommandManager.registerCommand(CoreBridgePlaceholderCommand::class.java, this)
        CommandManager.registerCommand(CoreBridgePacketCommand::class.java, this)
    }

    override fun onDisable() {
        pluginScope.cancel()
    }

    override fun debug(consumer: Consumer<Logger>) {
        val configuration = instance.configuration ?: return
        if (configuration.debug) {
            consumer.accept(instance.logger)
        }
    }

    override fun registerPacket(packetClass: Class<*>, pluginInstance: Any): Boolean = PacketManager.registerPacket(packetClass, pluginInstance)

    override fun handlePacket(
        player: Player,
        identifier: String,
        args: List<String>,
    ) {
        debug { logger ->
            logger.info("向玩家 [${player.name}] 发送标识符为 $identifier 参数为 $args 的数据包...")
        }
        PacketManager.handlePacket(identifier, player, args)
        Bukkit.getPluginManager().callEvent(CustomPacketEvent(player, identifier, args))
    }


}