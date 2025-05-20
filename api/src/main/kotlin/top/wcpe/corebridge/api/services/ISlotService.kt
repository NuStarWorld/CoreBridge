package top.wcpe.corebridge.api.services

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * 由 WCPE 在 2024/9/27 17:00 创建
 * <p>
 * Created by WCPE on 2024/9/27 17:00
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
interface ISlotService {
    /**
     * 发送物品到客户端的某个槽位
     * @param player 玩家对象
     * @param slotIdentifier 槽位标识符
     * @param itemStack 物品对象
     */
    fun sendItemStackToClientSlot(player: Player, slotIdentifier: String, itemStack: ItemStack?)

    fun getItemStackFromIdentifier(player: Player, slotIdentifier: String) : ItemStack?
}