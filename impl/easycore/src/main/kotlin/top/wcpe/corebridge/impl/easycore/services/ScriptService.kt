package top.wcpe.corebridge.impl.easycore.services

import org.bukkit.entity.Player
import top.wcpe.corebridge.api.services.IScriptService

/**
 * 由 WCPE 在 2024/9/30 5:01 创建
 * <p>
 * Created by WCPE on 2024/9/30 5:01
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object ScriptService : IScriptService {
    override fun sendScriptToClientForExecution(
        targetPlayer: Player,
        guiIndex: String,
        scriptContent: String,
    ) {
    }

    override fun sendScriptsToClientForExecution(
        targetPlayer: Player,
        guiIndex: String,
        vararg scriptContents: String,
    ) {
    }
}