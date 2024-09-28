package top.wcpe.corebridge.core.packet

import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import top.wcpe.corebridge.api.packet.Argument
import top.wcpe.corebridge.api.packet.Packet
import top.wcpe.corebridge.api.packet.PacketExecutor
import top.wcpe.corebridge.core.CoreBridge
import top.wcpe.corebridge.core.packet.extend.parentPacket
import top.wcpe.corebridge.core.packet.extend.singlePacket
import kotlin.reflect.KClass

/**
 * 由 WCPE 在 2024/9/26 10:26 创建
 * <p>
 * Created by WCPE on 2024/9/26 10:26
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object PacketManager {

    private val logger = CoreBridge.instance.logger

    /**
     * 包注册表
     */
    private val packetMap = mutableMapOf<String, Pair<Packet, JavaPlugin>>()

    /**
     * 插件包注册表
     */
    private val pluginPacketMap = mutableMapOf<String, MutableMap<String, Pair<Packet, JavaPlugin>>>()

    /**
     * 获取包注册表
     */
    @JvmStatic
    fun getPacketMap() = packetMap.toMap()

    /**
     * 获取插件包注册表
     */
    @JvmStatic
    fun getPluginPacketMap() = pluginPacketMap.toMap()

    /**
     * 获取指定插件的包注册表
     */
    @JvmStatic
    fun getPluginPacketMap(pluginName: String) = pluginPacketMap[pluginName]?.toMap() ?: mapOf()

    /**
     * 注册发包
     * 通过 实现 AbstractPacket 的实例 和 插件的主类实例来注册
     * @param packetInstance 包实例
     * @param pluginInstance 插件主类实例
     * @return 返回注册是否成功
     */
    @JvmStatic
    fun registerPacket(packetInstance: Packet, pluginInstance: Any): Boolean {
        if (pluginInstance !is JavaPlugin) {
            return false
        }
        val packetCommand = packetInstance.name
        val packetPair = packetMap[packetCommand]

        if (packetPair != null) {
            val packet = packetPair.first
            val instance = packetPair.second
            logger.info("检测到插件: [${instance.name}] 已注册包: ${packet.name}")
            if (packetMap.remove(packetCommand) != null) {
                logger.info("注销包: ${packet.name} 成功!")
            } else {
                logger.info("注销包: ${packet.name} 失败! 您的包可能并不会生效")
            }
        }
        pluginPacketMap.computeIfAbsent(pluginInstance.name) {
            mutableMapOf()
        }[packetCommand] = packetInstance to pluginInstance

        packetMap[packetCommand] = packetInstance to pluginInstance

        CoreBridge.api.getPacketService().addWhitelistPacket(packetCommand)
        return true
    }

    /**
     * 处理包执行
     * @param packetIdentifier 包标识符
     * @param player 玩家实例
     * @param data 包数据
     */
    fun handlePacket(packetIdentifier: String, player: Player, data: List<String>) {
        val packet = packetMap[packetIdentifier]?.first as? AbstractPacket ?: return
        packet.handleExecute(PlayerPacketSender(player), data)
    }

    /**
     * 处理包执行
     * @param packetIdentifier 包标识符
     * @param player 玩家实例
     * @param data 包数据
     */
    fun handlePacket(packetIdentifier: String, player: Player, data: Array<String?>) {
        val packet = packetMap[packetIdentifier]?.first as? AbstractPacket ?: return
        packet.handleExecute(PlayerPacketSender(player), data)
    }

    private fun parseAnnotation(packetClass: Class<*>): AbstractPacket? {
        return parseSinglePacket(packetClass) ?: parseParentPacket(packetClass)
    }

    private inline fun <reified T> findAnnotation(packetClass: Class<*>): T? {
        for (annotation in packetClass.annotations) {
            if (annotation is T) {
                return annotation
            }
        }
        return null
    }

    private fun newInstance(clazz: Class<*>): Any? {
        val constructor = clazz.getDeclaredConstructor()
        constructor.isAccessible = true
        return constructor.newInstance()
    }

    private fun parseSinglePacket(packetClass: Class<*>): AbstractPacket? {
        val singlePacketAnnotation = findAnnotation<SinglePacket>(packetClass) ?: return null

        val newInstance = newInstance(packetClass)

        return singlePacket(
            singlePacketAnnotation.name,
            singlePacketAnnotation.description,
            singlePacketAnnotation.arguments.map {
                Argument(
                    it.name,
                    it.required,
                    it.description
                )
            }.toList(),
            singlePacketAnnotation.usageMessage,
            newInstance as? PacketExecutor,
        )
    }

    private fun parseParentPacket(packetClass: Class<*>): AbstractPacket? {
        val parentPacketAnnotation = findAnnotation<ParentPacket>(packetClass) ?: return null

        val parentPacket = parentPacket(
            parentPacketAnnotation.name,
            parentPacketAnnotation.description,
            parentPacketAnnotation.usageMessage,
        )

        for (nestedClass in packetClass.declaredClasses) {
            parentPacket.addChildPacket(parseChildPacket(parentPacket, nestedClass) ?: continue)
        }

        return parentPacket
    }

    private fun parseChildPacket(
        parentInstance: ParentPacket, packetClass: Class<*>,
    ): ChildPacket? {
        val childPacketAnnotation = findAnnotation<ChildPacket>(packetClass) ?: return null

        val newInstance = newInstance(packetClass)

        return parentInstance.childPacket(
            childPacketAnnotation.name,
            childPacketAnnotation.description,
            childPacketAnnotation.arguments.map {
                Argument(
                    it.name,
                    it.required,
                    it.description
                )
            }.toList(),
            childPacketAnnotation.usageMessage,
            childPacketAnnotation.shouldDisplay,
            newInstance as? PacketExecutor,
        )
    }


    /**
     * 注册包
     * 通过含有包注解的类和插件主类实例来注册
     * @param packetClass 含 ParentPacket 注解的 javaClass 类
     * @param pluginInstance 插件主类实例
     * @return 返回注册是否成功
     */
    @JvmStatic
    fun registerPacket(packetClass: Class<*>, pluginInstance: Any): Boolean {
        return registerPacket(packetClass.kotlin, pluginInstance)
    }

    /**
     * 注册包
     * 通过含有包注解的类和插件主类实例来注册
     * @param packetClass 含 ParentPacket 注解的 KClass 类
     * @param pluginInstance 插件主类实例
     * @return 返回注册是否成功
     */
    @JvmStatic
    fun registerPacket(packetClass: KClass<*>, pluginInstance: Any): Boolean {
        val parseAnnotation = parseAnnotation(packetClass.java) ?: return false
        return registerPacket(parseAnnotation, pluginInstance)
    }
}