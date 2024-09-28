package top.wcpe.corebridge.impl.dragoncore.listener

import eos.moe.dragoncore.api.gui.event.CustomPacketEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import top.wcpe.corebridge.api.CoreBridge

/**
 * 由 WCPE 在 2024/9/26 17:04 创建
 * <p>
 * Created by WCPE on 2024/9/26 17:04
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