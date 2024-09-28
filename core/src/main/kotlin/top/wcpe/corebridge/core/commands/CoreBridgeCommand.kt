package top.wcpe.corebridge.core.commands

import kotlinx.coroutines.launch
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import top.wcpe.corebridge.core.CoreBridge
import top.wcpe.corebridge.core.packet.PacketManager
import top.wcpe.wcpelib.common.command.v2.CommandExecutor
import top.wcpe.wcpelib.common.command.v2.CommandSender
import top.wcpe.wcpelib.common.command.v2.TabCompleter
import top.wcpe.wcpelib.common.command.v2.annotation.Argument
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

    abstract class PacketCommand() : CoreBridgeCommand() {
        open val packetTabCompleteIndex = 2
        override fun tabComplete(commandSender: CommandSender<*>, args: Array<String>): List<String> {
            return if (args.size == packetTabCompleteIndex) {
                val keys = PacketManager.getPacketMap().keys
                if (args.isNotEmpty()) {
                    keys.filter {
                        it.lowercase().startsWith(args[packetTabCompleteIndex - 1].lowercase())
                    }
                } else {
                    keys
                }.toList()
            } else {
                emptyList()
            }
        }
    }

    @ChildCommand(
        name = "packet",
        description = "发包测试",
        aliases = ["p"],
        arguments = [Argument("identifier", true, "包的唯一标识符")],
        playerOnly = true
    )
    class PacketChildCommand : PacketCommand() {
        override val packetTabCompleteIndex = 1
        override fun execute(commandSender: CommandSender<*>, args: Array<String?>) {
            val player = commandSender.getCommandSender() as? Player ?: return
            val identifier = args[0] ?: return

            CoreBridge.instance.handlePacket(player, identifier, args.filterNotNull().slice(1..args.lastIndex))
        }
    }

    @ChildCommand(
        name = "packetToPlayer",
        description = "发包测试",
        aliases = ["ptp"],
        arguments = [Argument("playerName", true, "玩家名称"), Argument("identifier", true, "包的唯一标识符")]
    )
    class PacketToPlayerChildCommand : PacketCommand() {
        override fun execute(commandSender: CommandSender<*>, args: Array<String?>) {
            val player = getPlayer(commandSender, args[0]) ?: return
            val identifier = args[1] ?: return

            CoreBridge.instance.handlePacket(player, identifier, args.filterNotNull().slice(2..args.lastIndex))
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