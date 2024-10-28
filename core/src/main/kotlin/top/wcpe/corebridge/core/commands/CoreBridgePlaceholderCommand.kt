package top.wcpe.corebridge.core.commands

import top.wcpe.corebridge.core.CoreBridge
import top.wcpe.wcpelib.common.command.v2.CommandSender
import top.wcpe.wcpelib.common.command.v2.annotation.Argument
import top.wcpe.wcpelib.common.command.v2.annotation.ChildCommand
import top.wcpe.wcpelib.common.command.v2.annotation.ParentCommand

/**
 * 由 WCPE 在 2024/9/27 12:26 创建
 * <p>
 * Created by WCPE on 2024/9/27 12:26
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
@ParentCommand(
    name = "CoreBridgePlaceholder",
    description = "CoreBridge 占位符服务相关命令",
    aliases = ["cbp", "cbplaceholder","coreplaceholder"]
)
class CoreBridgePlaceholderCommand {

    @ChildCommand(
        name = "sendPlaceholder",
        description = "发送占位符",
        aliases = ["sp"],
        arguments = [Argument("playerName", true, "玩家名称"), Argument(
            "placeholder",
            true,
            "占位符"
        ), Argument("value", true, "值")]
    )
    class SendPlaceholderChildCommand : CoreBridgeCommand() {
        override fun execute(commandSender: CommandSender<*>, args: Array<String?>) {
            val player = getPlayer(commandSender, args[0]) ?: return
            val placeholder = args[1] ?: return
            CoreBridge.api.getPlaceholderService().sendPlaceholder(player, placeholder, args.drop(2).joinToString(" "))
        }
    }

    @ChildCommand(
        name = "removePlaceholder",
        description = "删除占位符",
        aliases = ["rp"],
        arguments = [Argument("playerName", true, "玩家名称"), Argument(
            "placeholder",
            true,
            "占位符"
        ), Argument("startsWith", false, "根据开始的字符串匹配")]
    )
    class RemovePlaceholderChildCommand : CoreBridgeCommand() {
        override fun execute(commandSender: CommandSender<*>, args: Array<String?>) {
            val player = getPlayer(commandSender, args[0]) ?: return
            val placeholder = args[1] ?: return
            val startsWith = args[2].toBoolean()

            CoreBridge.api.getPlaceholderService().removePlaceholder(player, placeholder, startsWith)
        }
    }
}