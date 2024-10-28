package top.wcpe.corebridge.core.commands

import kotlinx.coroutines.launch
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import top.wcpe.corebridge.core.CoreBridge
import top.wcpe.wcpelib.common.command.v2.CommandExecutor
import top.wcpe.wcpelib.common.command.v2.CommandSender
import top.wcpe.wcpelib.common.command.v2.TabCompleter
import top.wcpe.wcpelib.common.command.v2.annotation.ChildCommand
import top.wcpe.wcpelib.common.command.v2.annotation.ParentCommand

/**
 * 由 WCPE 在 2024/9/27 11:42 创建
 * <p>
 * Created by WCPE on 2024/9/27 11:42
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
@ParentCommand(name = "CoreBridge", description = "CoreBridge 插件相关命令", aliases = ["cb", "coreb"])
abstract class CoreBridgeCommand : CommandExecutor, TabCompleter {

    /**
     * 获取玩家对象
     * @param commandSender 命令发送者
     * @param playerArg 玩家名称
     * @return 玩家对象
     */
    fun getPlayer(commandSender: CommandSender<*>, playerArg: String?): Player? {
        val sender = commandSender.getCommandSender() as org.bukkit.command.CommandSender
        if (!sender.isOp && playerArg != sender.name) {
            return null
        }
        val player = Bukkit.getPlayerExact(playerArg)
        if (player == null || !player.isOnline) {
            commandSender.sendMessage("§c玩家 §e${playerArg} §c不存在或不在线!")
            return null
        }
        return player
    }

    override fun tabComplete(commandSender: CommandSender<*>, args: Array<String>): List<String> {
        return if (args.size == 1) {
            if (args.isNotEmpty()) {
                Bukkit.getOnlinePlayers().filter { it.name.lowercase().startsWith(args[0].lowercase()) }.map { it.name }
            } else {
                Bukkit.getOnlinePlayers().map { it.name }
            }
        } else {
            emptyList()
        }
    }


    @ChildCommand("reload", "重载插件", ["r"])
    class ReloadChildCommand : CommandExecutor {
        override fun execute(commandSender: CommandSender<*>, args: Array<String?>) {
            CoreBridge.pluginScope.launch {
                CoreBridge.instance.reloadOtherConfig()
                commandSender.sendMessage("重载插件成功!")
            }
        }
    }

}