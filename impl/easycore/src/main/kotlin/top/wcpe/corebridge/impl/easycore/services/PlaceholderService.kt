package top.wcpe.corebridge.impl.easycore.services

import com.yuankong.easycore.api.EasyCoreAPI
import org.bukkit.entity.Player
import top.wcpe.corebridge.api.services.IPlaceholderService

/**
 * 由 WCPE 在 2024/9/26 15:55 创建
 * <p>
 * Created by WCPE on 2024/9/26 15:55
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object PlaceholderService : IPlaceholderService {
    override fun sendPlaceholder(player: Player, placeholder: String, value: String) {
        EasyCoreAPI.sendPlaceholder(player, mapOf(placeholder to value))
    }

    override fun sendPlaceholderMap(
        player: Player,
        placeholderMap: Map<String, String>,
    ) {
        EasyCoreAPI.sendPlaceholder(player, placeholderMap)
    }

    override fun sendPlaceholders(
        player: Player,
        vararg placeholders: Pair<String, String>,
    ) {
        EasyCoreAPI.sendPlaceholder(player, placeholders.toMap())
    }

    override fun removePlaceholder(
        player: Player,
        placeholder: String,
        startsWith: Boolean,
    ) {
        if (startsWith) {
            EasyCoreAPI.removePlaceholderStartswith(player, placeholder)
        } else {
            EasyCoreAPI.removePlaceholder(player, placeholder)
        }
    }

    override fun removePlaceholders(player: Player, vararg placeholders: String) {
        placeholders.forEach { placeholder -> EasyCoreAPI.removePlaceholder(player, placeholder) }
    }

}