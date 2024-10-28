package top.wcpe.corebridge.core.commands

import org.bukkit.entity.Player
import top.wcpe.corebridge.core.CoreBridge
import top.wcpe.corebridge.core.packet.PacketManager
import top.wcpe.corebridge.core.packet.ParentPacket
import top.wcpe.wcpelib.common.command.v2.CommandSender
import top.wcpe.wcpelib.common.command.v2.annotation.Argument
import top.wcpe.wcpelib.common.command.v2.annotation.ChildCommand
import top.wcpe.wcpelib.common.command.v2.annotation.ParentCommand

/**
 * 由 WCPE 在 2024/9/29 15:17 创建
 * <p>
 * Created by WCPE on 2024/9/29 15:17
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
@ParentCommand(name = "CoreBridgePacket", description = "CoreBridge 数据包服务相关命令", aliases = ["cbpack", "corepack"])
abstract class CoreBridgePacketCommand : CoreBridgeCommand() {

    open val packetTabCompleteIndex = 2
    override fun tabComplete(commandSender: CommandSender<*>, args: Array<String>): List<String> {
        if (args.size == packetTabCompleteIndex) {
            val keys = PacketManager.getPacketMap().keys
            return if (keys.isNotEmpty()) {
                keys.filter {
                    it.lowercase().startsWith(args[packetTabCompleteIndex - 1].lowercase())
                }
            } else {
                keys
            }.toList()
        }
        val childCommandIndex = packetTabCompleteIndex + 1
        if (args.size == childCommandIndex) {
            val packetPair = PacketManager.getPacketMap()[args[childCommandIndex - 2]]
            if (packetPair != null) {
                val packet = packetPair.first
                if (packet is ParentPacket) {
                    val keys = packet.getChildCommandMap().keys
                    return if (keys.isNotEmpty()) {
                        keys.filter {
                            it.lowercase().startsWith(args[childCommandIndex - 1].lowercase())
                        }
                    } else {
                        keys
                    }.toList()
                }
            }
        }
        return super.tabComplete(commandSender, args)
    }

    @ChildCommand(
        name = "packet",
        description = "发包测试",
        aliases = ["p"],
        arguments = [Argument("identifier", true, "包的唯一标识符")],
        playerOnly = true
    )
    class PacketChildCommand : CoreBridgePacketCommand() {
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
    class PacketToPlayerChildCommand : CoreBridgePacketCommand() {
        override fun execute(commandSender: CommandSender<*>, args: Array<String?>) {
            val player = getPlayer(commandSender, args[0]) ?: return
            val identifier = args[1] ?: return

            CoreBridge.instance.handlePacket(player, identifier, args.filterNotNull().slice(2..args.lastIndex))
        }
    }
}