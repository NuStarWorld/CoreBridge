package top.wcpe.corebridge.impl.easycore.services

import com.yuankong.easycore.api.ui.SlotAPI
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import top.wcpe.corebridge.api.services.ISlotService

/**
 * 由 WCPE 在 2024/9/27 17:19 创建
 * <p>
 * Created by WCPE on 2024/9/27 17:19
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object SlotService : ISlotService {
    override fun sendItemStackToClientSlot(player: Player, slotIdentifier: String, itemStack: ItemStack?) {
        SlotAPI.sendCacheItemStack(player, slotIdentifier, itemStack)
    }

    override fun getSlotItem(
        player: Player,
        slotIdentifier: String,
    ): ItemStack? {
        return SlotAPI.getExtraSlotItem(player, slotIdentifier)
    }

    override fun setSlotItem(
        player: Player,
        slotIdentifier: String,
        itemStack: ItemStack?,
    ) {
        SlotAPI.setExtraSlotItem(player, slotIdentifier, itemStack)
    }

    override fun setSlotItem(
        player: Player,
        slotIdentifier: String,
        itemStack: ItemStack?,
        syncToClient: Boolean,
    ) {
        SlotAPI.setExtraSlotItem(player, slotIdentifier, itemStack)
        SlotAPI.sendCacheItemStack(player, slotIdentifier, itemStack)
    }
}