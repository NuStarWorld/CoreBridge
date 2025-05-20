package top.wcpe.corebridge.impl.easycore.services

import com.yuankong.easycore.api.ui.SlotAPI
import com.yuankong.easycore.core.data.EasyPlayerData
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
    override fun getItemStackFromIdentifier(player: Player, slotIdentifier: String) : ItemStack? {
        return EasyPlayerData.getPlayerData(player).cacheItems[slotIdentifier]?.itemStack
    }
}