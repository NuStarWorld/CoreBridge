package top.wcpe.corebridge.api.event

import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

/**
 * 由 WCPE 在 2024/9/27 10:53 创建
 * <p>
 * Created by WCPE on 2024/9/27 10:53
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
/**
 * 自定义数据包事件
 * @param player 玩家
 * @param identifier 事件标识符
 * @param args 事件参数
 */
data class CustomPacketEvent(
    private val player: Player,
    val identifier: String,
    val args: List<String>,
) : PlayerEvent(player), Cancellable {
    companion object {
        @JvmStatic
        val handlerList: HandlerList = HandlerList()
    }

    private var cancelled = false


    override fun getHandlers(): HandlerList {
        return handlerList
    }


    override fun isCancelled(): Boolean {
        return this.cancelled
    }

    override fun setCancelled(cancelled: Boolean) {
        this.cancelled = cancelled
    }
}
