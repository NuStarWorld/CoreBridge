package top.wcpe.corebridge.impl.germplugin.services

import org.bukkit.entity.Player
import top.wcpe.corebridge.api.services.IGuiService

/**
 * 由 WCPE 在 2024/9/26 16:47 创建
 * <p>
 * Created by WCPE on 2024/9/26 16:47
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object GuiService : IGuiService {
    override fun requestOpenGui(player: Player, index: String) {
    }

    override fun requestCloseGui(player: Player, index: String) {
    }

    override fun requestCloseGui(player: Player) {
    }
}