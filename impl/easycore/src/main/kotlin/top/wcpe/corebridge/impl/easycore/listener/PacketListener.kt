package top.wcpe.corebridge.impl.easycore.listener

import com.yuankong.easycore.api.event.CustomPacketEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import top.wcpe.corebridge.api.CoreBridge

/**
 * 由 WCPE 在 2024/9/27 11:00 创建
 * <p>
 * Created by WCPE on 2024/9/27 11:00
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object PacketListener : Listener {

    @EventHandler
    fun listenerCustomPacketEvent(e: CustomPacketEvent) {
        val player = e.player
        val identifier = e.identifier
        CoreBridge.instance.handlePacket(player, identifier, e.data)
    }

}