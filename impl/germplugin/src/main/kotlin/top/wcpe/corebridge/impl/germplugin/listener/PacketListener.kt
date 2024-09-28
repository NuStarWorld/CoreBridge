package top.wcpe.corebridge.impl.germplugin.listener

import com.germ.germplugin.api.event.GermReceiveDosEvent
import com.germ.germplugin.api.event.gui.GermGuiSlotPreClickEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import top.wcpe.corebridge.api.CoreBridge
import top.wcpe.corebridge.api.event.CoreSlotClickEvent

/**
 * 由 WCPE 在 2024/9/27 11:01 创建
 * <p>
 * Created by WCPE on 2024/9/27 11:01
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object PacketListener : Listener {

    @EventHandler(ignoreCancelled = true)
    fun onGermReceiveDos(e: GermReceiveDosEvent) {
        val player = e.player
        val identifier = e.dosId
        val args = e.dosContent.split(" ")
        CoreBridge.instance.handlePacket(player, identifier, args)
    }

    @EventHandler(ignoreCancelled = true)
    fun onGermGuiSlotClickEvent(e: GermGuiSlotPreClickEvent) {
        val player = e.player
        val slotIdentity = e.slotIdentity
        Bukkit.getPluginManager().callEvent(CoreSlotClickEvent(player, slotIdentity))
    }
}