package top.wcpe.corebridge.core

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import top.wcpe.corebridge.api.CoreBridgeApi
import top.wcpe.corebridge.api.services.IGuiService
import top.wcpe.corebridge.api.services.IPacketService
import top.wcpe.corebridge.api.services.IPlaceholderService
import top.wcpe.corebridge.api.services.IScriptService
import top.wcpe.corebridge.api.services.ISlotService

/**
 * 由 WCPE 在 2024/9/26 12:09 创建
 * <p>
 * Created by WCPE on 2024/9/26 12:09
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
class CoreBridgeManager(private val apis: List<CoreBridgeApi>) : CoreBridgeApi, IPlaceholderService, IGuiService,
    IPacketService, ISlotService, IScriptService {

    override fun getPlaceholderService(): IPlaceholderService = this
    override fun getSlotService(): ISlotService = this
    override fun getGuiService(): IGuiService = this
    override fun getPacketService(): IPacketService = this
    override fun getScriptService(): IScriptService = this
    override fun sendPlaceholder(player: Player, placeholder: String, value: String) {
        apis.forEach { coreBridgeApi ->
            coreBridgeApi.getPlaceholderService().sendPlaceholder(player, placeholder, value)
        }
    }

    override fun sendPlaceholderMap(
        player: Player,
        placeholderMap: Map<String, String>,
    ) {
        apis.forEach { coreBridgeApi ->
            coreBridgeApi.getPlaceholderService().sendPlaceholderMap(player, placeholderMap)
        }
    }

    override fun sendPlaceholders(
        player: Player,
        vararg placeholders: Pair<String, String>,
    ) {
        apis.forEach { coreBridgeApi ->
            coreBridgeApi.getPlaceholderService().sendPlaceholders(player, *placeholders)
        }
    }

    override fun removePlaceholder(
        player: Player,
        placeholder: String,
        startsWith: Boolean,
    ) {
        apis.forEach { coreBridgeApi ->
            coreBridgeApi.getPlaceholderService().removePlaceholder(player, placeholder, startsWith)
        }
    }

    override fun removePlaceholders(player: Player, vararg placeholders: String) {
        apis.forEach { coreBridgeApi ->
            coreBridgeApi.getPlaceholderService().removePlaceholders(player, *placeholders)
        }
    }

    override fun requestOpenGui(player: Player, index: String) {
        apis.forEach { coreBridgeApi ->
            coreBridgeApi.getGuiService().requestOpenGui(player, index)
        }
    }

    override fun requestCloseGui(player: Player, index: String) {
        apis.forEach { coreBridgeApi ->
            coreBridgeApi.getGuiService().requestCloseGui(player, index)
        }
    }

    override fun requestCloseGui(player: Player) {
        apis.forEach { coreBridgeApi ->
            coreBridgeApi.getGuiService().requestCloseGui(player)
        }
    }

    override fun getGuiIndexList(): List<String> {
        return apis.first().getGuiService().getGuiIndexList()
    }

    override fun reloadGui(player: Player, index: String) {
        apis.forEach { coreBridgeApi ->
            coreBridgeApi.getGuiService().reloadGui(player, index)
        }
    }

    override fun addWhitelistPacket(identifier: String) {
        apis.forEach { coreBridgeApi ->
            coreBridgeApi.getPacketService().addWhitelistPacket(identifier)
        }
    }

    override fun sendItemStackToClientSlot(
        player: Player,
        slotIdentifier: String,
        itemStack: ItemStack?,
    ) {
        apis.forEach { coreBridgeApi ->
            coreBridgeApi.getSlotService().sendItemStackToClientSlot(player, slotIdentifier, itemStack)
        }
    }

    override fun sendScriptToClientForExecution(targetPlayer: Player, guiIndex: String, scriptContent: String) {
        apis.forEach { coreBridgeApi ->
            coreBridgeApi.getScriptService().sendScriptToClientForExecution(targetPlayer, guiIndex, scriptContent)
        }
    }

    override fun sendScriptsToClientForExecution(
        targetPlayer: Player,
        guiIndex: String,
        vararg scriptContents: String,
    ) {
        apis.forEach { coreBridgeApi ->
            coreBridgeApi.getScriptService().sendScriptsToClientForExecution(targetPlayer, guiIndex, *scriptContents)
        }
    }


}