package top.wcpe.corebridge.api.event

import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

/**
 * 由 WCPE 在 2024/9/27 10:54 创建
 * <p>
 * Created by WCPE on 2024/9/27 10:54
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
/**
 * 玩家点击槽事件
 * @param player 玩家
 * @param slotIdentity 点击的槽位的唯一标识符
 */
data class CoreSlotClickEvent(
    private val player: Player,
    val slotIdentity: String,
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
