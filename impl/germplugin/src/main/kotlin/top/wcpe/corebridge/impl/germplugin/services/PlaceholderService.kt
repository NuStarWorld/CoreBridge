package top.wcpe.corebridge.impl.germplugin.services

import com.germ.germplugin.api.GermPacketAPI
import org.bukkit.entity.Player
import top.wcpe.corebridge.api.services.IPlaceholderService
import kotlin.collections.component1
import kotlin.collections.component2

/**
 * 由 WCPE 在 2024/9/26 15:47 创建
 * <p>
 * Created by WCPE on 2024/9/26 15:47
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object PlaceholderService : IPlaceholderService {
    override fun sendPlaceholder(player: Player, placeholder: String, value: String) {
        GermPacketAPI.sendPlaceholder(player, placeholder, value)
    }

    override fun sendPlaceholderMap(
        player: Player,
        placeholderMap: Map<String, String>,
    ) {
        placeholderMap.forEach { (placeholder, value) ->
            GermPacketAPI.sendPlaceholder(player, placeholder, value)
        }
    }

    override fun sendPlaceholders(
        player: Player,
        vararg placeholders: Pair<String, String>,
    ) {
        placeholders.forEach { (placeholder, value) ->
            GermPacketAPI.sendPlaceholder(player, placeholder, value)
        }
    }

    override fun removePlaceholder(
        player: Player,
        placeholder: String,
        startsWith: Boolean,
    ) {
        if (startsWith) {
            GermPacketAPI.removePlaceholderIfContain(player, placeholder)
        } else {
            GermPacketAPI.removePlaceholder(player, placeholder)
        }
    }

    override fun removePlaceholders(player: Player, vararg placeholders: String) {
        placeholders.forEach { placeholder ->
            GermPacketAPI.removePlaceholderIfContain(player, placeholder)
        }
    }

}