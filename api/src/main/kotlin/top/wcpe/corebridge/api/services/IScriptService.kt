package top.wcpe.corebridge.api.services

import org.bukkit.entity.Player

/**
 * 由 WCPE 在 2024/9/28 10:46 创建
 * <p>
 * Created by WCPE on 2024/9/28 10:46
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
interface IScriptService {
    /**
     * 向指定的客户端玩家发送脚本并在客户端执行。
     * @param targetPlayer 目标客户端玩家对象。
     * @param guiIndex GUI索引。
     * @param scriptContent 要发送的脚本内容。
     */
    fun sendScriptToClientForExecution(targetPlayer: Player, guiIndex: String, scriptContent: String)

    /**
     * 向指定的客户端玩家发送多个脚本并在客户端执行。
     * @param targetPlayer 目标客户端玩家对象。
     * @param guiIndex GUI索引。
     * @param scriptContents 要发送的脚本内容。
     */
    fun sendScriptsToClientForExecution(targetPlayer: Player, guiIndex: String, vararg scriptContents: String)

}