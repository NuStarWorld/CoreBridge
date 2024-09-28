package top.wcpe.corebridge.impl.germplugin.services

import com.germ.germplugin.api.GermPacketAPI
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import top.wcpe.corebridge.api.services.ISlotService

/**
 * 由 WCPE 在 2024/9/27 17:22 创建
 * <p>
 * Created by WCPE on 2024/9/27 17:22
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object SlotService : ISlotService {
    override fun sendItemStackToClientSlot(
        player: Player,
        slotIdentifier: String,
        itemStack: ItemStack?,
    ) {
        GermPacketAPI.sendSlotItemStack(player, slotIdentifier, itemStack)
    }

}