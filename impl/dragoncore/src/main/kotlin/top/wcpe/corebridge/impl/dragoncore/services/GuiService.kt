package top.wcpe.corebridge.impl.dragoncore.services

import eos.moe.dragoncore.network.PacketSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import top.wcpe.corebridge.api.services.IGuiService
import top.wcpe.corebridge.impl.dragoncore.DragonCoreCoreBridgeApiImpl
import java.io.File

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

    override fun getGuiIndexList(): List<String> {
        val guiDirFile = DragonCoreCoreBridgeApiImpl.guiDirFile
        return guiDirFile.walk().filter { it.isFile }.mapNotNull { file ->

            val filePath = file.path
            val fullPath = filePath.replace("${guiDirFile}${File.separatorChar}", "")

            // 查找最后一个点的位置，即扩展名的起始位置
            val lastDotIndex = fullPath.lastIndexOf('.')

            // 如果找到了点，并且点不在文件名的开头位置
            if (lastDotIndex == -1 || lastDotIndex <= fullPath.lastIndexOf(File.separatorChar)) {
                return@mapNotNull null
            }
            val fullPathWithoutExtension = fullPath.substring(0, lastDotIndex)
            return@mapNotNull fullPathWithoutExtension.replace("${File.separatorChar}", "/")
        }.toList()

    }

    override fun reloadGui(player: Player, index: String) {
        val guiDirFile = DragonCoreCoreBridgeApiImpl.guiDirFile
        val guiFile = File(guiDirFile, "/$index.yml")
        val yaml = YamlConfiguration.loadConfiguration(guiFile)
        PacketSender.sendYaml(player, "Gui/$index", yaml)
    }

}