package top.wcpe.corebridge.api.services

import org.bukkit.entity.Player

/**
 * 由 WCPE 在 2024/9/26 15:03 创建
 * <p>
 * Created by WCPE on 2024/9/26 15:03
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
interface IPlaceholderService {
    /**
     * 发送单个占位符
     * @param player 玩家
     * @param placeholder 占位符
     * @param value 值
     */
    fun sendPlaceholder(player: Player, placeholder: String, value: String)

    /**
     * 发送多个占位符
     * @param player 玩家
     * @param placeholderMap 占位符映射
     * @param value 值
     */
    fun sendPlaceholderMap(player: Player, placeholderMap: Map<String, String>)

    /**
     * 发送多个占位符
     * @param player 玩家
     * @param placeholders 占位符列表
     */
    fun sendPlaceholders(player: Player, vararg placeholders: Pair<String, String>)


    /**
     * 删除单个占位符
     * @param player 玩家
     * @param placeholder 占位符
     */
    fun removePlaceholder(player: Player, placeholder: String, startsWith: Boolean = false)

    /**
     * 删除多个占位符
     * @param player 玩家
     * @param placeholders 占位符列表
     */
    fun removePlaceholders(player: Player, vararg placeholders: String)


}