package top.wcpe.corebridge.api

import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.util.function.Consumer
import java.util.logging.Logger

/**
 * 由 WCPE 在 2024/9/26 18:46 创建
 * <p>
 * Created by WCPE on 2024/9/26 18:46
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
interface CoreBridgeInstance {

    /**
     * 调试消息
     * @param consumer 消费者
     */
    fun debug(consumer: Consumer<Logger>)

    /**
     * 注册数据包
     * 通过含有包注解的类和插件主类实例来注册
     * @param packetClass 含 ParentPacket 注解的 javaClass 类
     * @return 返回注册是否成功
     */
    fun registerPacket(packetClass: Class<*>, pluginInstance: Any): Boolean

    /**
     * 处理数据包
     * @param player 玩家
     * @param identifier 标识符
     * @param args 参数列表
     */
    fun handlePacket(player: Player, identifier: String, args: List<String>)


}