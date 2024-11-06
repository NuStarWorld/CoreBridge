package top.wcpe.corebridge.impl.easycore.services

import com.yuankong.easycore.api.EasyCoreAPI
import org.bukkit.entity.Player
import top.wcpe.corebridge.api.services.IGuiService

/**
 * 由 WCPE 在 2024/9/26 16:46 创建
 * <p>
 * Created by WCPE on 2024/9/26 16:46
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object GuiService : IGuiService {
    override fun requestOpenGui(player: Player, index: String) {
        EasyCoreAPI.openGui(player, index)
    }

    override fun requestCloseGui(player: Player, index: String) {
        EasyCoreAPI.closeGui(player)
    }

    override fun requestCloseGui(player: Player) {
        EasyCoreAPI.closeGui(player)
    }

    override fun getGuiIndexList(): List<String> {
        TODO("Not yet implemented")
    }

    override fun reloadGui(player: Player, index: String) {
        TODO("Not yet implemented")
    }
}