package top.wcpe.corebridge.api.services

import org.bukkit.entity.Player

/**
 * 由 WCPE 在 2024/9/26 16:33 创建
 * <p>
 * Created by WCPE on 2024/9/26 16:33
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
interface IGuiService {
    /**
     * 打开 GUI
     * @param player 玩家
     * @param index GUI 索引
     */
    fun requestOpenGui(player: Player, index: String)

    /**
     * 关闭 GUI
     * @param player 玩家
     * @param index GUI 索引
     */
    fun requestCloseGui(player: Player, index: String)

    /**
     * 关闭当前 GUI
     * @param player 玩家
     */
    fun requestCloseGui(player: Player)
}