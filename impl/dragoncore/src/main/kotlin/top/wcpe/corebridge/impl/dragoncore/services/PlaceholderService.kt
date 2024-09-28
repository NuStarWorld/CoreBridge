package top.wcpe.corebridge.impl.dragoncore.services

import eos.moe.dragoncore.network.PacketSender
import org.bukkit.entity.Player
import top.wcpe.corebridge.api.services.IPlaceholderService

/**
 * 由 WCPE 在 2024/9/26 15:08 创建
 * <p>
 * Created by WCPE on 2024/9/26 15:08
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object PlaceholderService : IPlaceholderService {
    override fun sendPlaceholder(player: Player, placeholder: String, value: String) {
        PacketSender.sendSyncPlaceholder(player, mapOf(placeholder to value))
    }

    override fun sendPlaceholderMap(
        player: Player,
        placeholderMap: Map<String, String>,
    ) {
        PacketSender.sendSyncPlaceholder(player, placeholderMap)
    }

    override fun sendPlaceholders(
        player: Player,
        vararg placeholders: Pair<String, String>,
    ) {
        PacketSender.sendSyncPlaceholder(player, placeholders.toMap())
    }

    override fun removePlaceholder(
        player: Player,
        placeholder: String,
        startsWith: Boolean,
    ) {
        PacketSender.sendDeletePlaceholderCache(player, placeholder, startsWith)
    }

    override fun removePlaceholders(player: Player, vararg placeholders: String) {
        placeholders.forEach { placeholder ->
            PacketSender.sendDeletePlaceholderCache(player, placeholder, false)
        }
    }

}