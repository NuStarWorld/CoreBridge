package top.wcpe.corebridge.core.commands

import top.wcpe.corebridge.core.CoreBridge
import top.wcpe.wcpelib.common.command.v2.CommandSender
import top.wcpe.wcpelib.common.command.v2.annotation.Argument
import top.wcpe.wcpelib.common.command.v2.annotation.ChildCommand
import top.wcpe.wcpelib.common.command.v2.annotation.ParentCommand

/**
 * 由 WCPE 在 2024/9/27 14:29 创建
 * <p>
 * Created by WCPE on 2024/9/27 14:29
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
@Suppress("unused")
@ParentCommand(
    name = "CoreBridgeGui", description = "CoreBridge 图形界面服务相关命令", aliases = ["cbg", "cbGui"]
)
abstract class CoreBridgeGuiCommand : CoreBridgeCommand() {

    override fun tabComplete(commandSender: CommandSender<*>, args: Array<String>): List<String> {

        if (args.size == 2) {
            val guiIndexList = CoreBridge.api.getGuiService().getGuiIndexList()

            if (guiIndexList.isNotEmpty()) {
                return guiIndexList.filter {
                    it.lowercase().startsWith(args[1].lowercase())
                }
            }
        }
        return super.tabComplete(commandSender, args)
    }

    @ChildCommand(
        name = "requestOpenGui",
        description = "发送打开图形界面请求",
        aliases = ["rog"],
        arguments = [Argument("playerName", true, "玩家名称"), Argument("index", true, "界面索引")]
    )
    class RequestOpenGuiChildCommand : CoreBridgeGuiCommand() {
        override fun execute(commandSender: CommandSender<*>, args: Array<String?>) {
            val player = getPlayer(commandSender, args[0]) ?: return
            val index = args[1] ?: return
            CoreBridge.api.getGuiService().requestOpenGui(player, index)
            commandSender.sendMessage("已发送请求打开索引为 $index 的图形界面")
        }
    }

    @ChildCommand(
        name = "requestCloseGui",
        description = "发送关闭图形界面请求",
        aliases = ["rcg"],
        arguments = [Argument("playerName", true, "玩家名称"), Argument("index", true, "界面索引")]
    )
    class RequestCloseGuiChildCommand : CoreBridgeGuiCommand() {
        override fun execute(commandSender: CommandSender<*>, args: Array<String?>) {
            val player = getPlayer(commandSender, args[0]) ?: return
            val index = args[1] ?: return
            CoreBridge.api.getGuiService().requestCloseGui(player, index)
            commandSender.sendMessage("已发送请求关闭索引为 $index 的图形界面")
        }
    }

    @ChildCommand(
        name = "reloadGui",
        description = "重载指定界面",
        aliases = ["rg"],
        arguments = [Argument("playerName", true, "玩家名称"), Argument("index", true, "界面索引")]
    )
    class ReloadGuiChildCommand : CoreBridgeGuiCommand() {
        override fun execute(commandSender: CommandSender<*>, args: Array<String?>) {
            val player = getPlayer(commandSender, args[0]) ?: return
            val index = args[1] ?: return
            CoreBridge.api.getGuiService().reloadGui(player, index)
            commandSender.sendMessage("已将索引为 $index 的图形界面重载请求发送给玩家 ${player.name}!")
        }
    }


}