package top.wcpe.corebridge.impl.germplugin.services

import com.germ.germplugin.api.GermClientAPI
import com.germ.germplugin.api.GermPacketAPI
import com.germ.germplugin.api.dynamic.gui.GermGuiScreen
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import top.wcpe.corebridge.api.services.IGuiService
import top.wcpe.corebridge.impl.germplugin.GermPluginCoreBridgeApiImpl

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
        GermPacketAPI.openGui(player, index)
    }

    override fun requestCloseGui(player: Player, index: String) {
    }

    override fun requestCloseGui(player: Player) {
    }

    override fun getGuiIndexList(): List<String> {
        val guiDirFile = GermPluginCoreBridgeApiImpl.guiDirFile
        return guiDirFile.walk().filter { it.isFile }.mapNotNull { file ->
            val yaml = YamlConfiguration.loadConfiguration(file)
            yaml.getKeys(false).firstOrNull()
        }.toList()
    }

    override fun reloadGui(player: Player, index: String) {


    }
}