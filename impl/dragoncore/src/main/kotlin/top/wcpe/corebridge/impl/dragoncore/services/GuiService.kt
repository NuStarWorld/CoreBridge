package top.wcpe.corebridge.impl.dragoncore.services

import eos.moe.dragoncore.network.PacketSender
import org.bukkit.entity.Player
import top.wcpe.corebridge.api.services.IGuiService

/**
 * 由 WCPE 在 2024/9/26 16:39 创建
 * <p>
 * Created by WCPE on 2024/9/26 16:39
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object GuiService : IGuiService {
    override fun requestOpenGui(player: Player, index: String) {
        PacketSender.sendOpenGui(player, index)
    }

    override fun requestCloseGui(player: Player, index: String) {
        PacketSender.sendRunFunction(player, index, "方法.关闭界面", false)
    }

    override fun requestCloseGui(player: Player) {
        player.closeInventory()
    }

}