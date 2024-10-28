package top.wcpe.corebridge.impl.dragoncore.services

import eos.moe.dragoncore.network.PacketSender
import org.bukkit.entity.Player
import top.wcpe.corebridge.api.services.IScriptService

/**
 * 由 WCPE 在 2024/9/29 15:08 创建
 * <p>
 * Created by WCPE on 2024/9/29 15:08
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object ScriptService : IScriptService {
    override fun sendScriptToClientForExecution(targetPlayer: Player, guiIndex: String, scriptContent: String) {
        PacketSender.sendRunFunction(targetPlayer, guiIndex, scriptContent, false)
    }

    override fun sendScriptsToClientForExecution(
        targetPlayer: Player,
        guiIndex: String,
        vararg scriptContents: String,
    ) {
        PacketSender.sendRunFunction(targetPlayer, guiIndex, scriptContents.joinToString(""), false)
    }
}