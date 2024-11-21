package top.wcpe.corebridge.api.extend

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import top.wcpe.corebridge.api.CoreBridge
import top.wcpe.corebridge.api.placeholder.IPlaceholder

/**
 * 由 WCPE 在 2024/11/21 11:24 创建
 * <p>
 * Created by WCPE on 2024/11/21 11:24
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */

/**
 * 发送占位符
 */
fun Player.sendPlaceholder(
    placeholder: IPlaceholder,
    value: String,
    vararg placeholders: Pair<String, String>,
) {
    val placeholderMap = placeholders.toMap()
    placeholder.consumeAllFormats(placeholderMap) { placeholderStr ->
        CoreBridge.api.getPlaceholderService().sendPlaceholder(this, placeholderStr, value)
    }
}

/**
 * 发送 ItemStack 到客户端的某个槽位
 */
fun Player.sendItemStackToClientSlot(
    placeholder: IPlaceholder,
    itemStack: ItemStack?,
    vararg placeholders: Pair<String, String>,
) {
    val placeholderMap = placeholders.toMap()
    placeholder.consumeAllFormats(placeholderMap) { placeholderStr ->
        CoreBridge.api.getSlotService().sendItemStackToClientSlot(this, placeholderStr, itemStack)
    }
}

