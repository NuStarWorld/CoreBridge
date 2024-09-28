package top.wcpe.corebridge.core.packet

import org.bukkit.command.CommandSender
import top.wcpe.corebridge.api.packet.PacketSender

/**
 * 由 WCPE 在 2024/9/26 10:26 创建
 * <p>
 * Created by WCPE on 2024/9/26 10:26
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
@JvmInline
value class PlayerPacketSender(private val commandSender: CommandSender) :
    PacketSender<CommandSender> {

    override fun getPacketSender(): CommandSender {
        return commandSender
    }

    override fun getName(): String {
        return commandSender.name
    }


    override fun sendMessage(message: String) {
        commandSender.sendMessage(message)
    }

    override fun isOp(): Boolean {
        return commandSender.isOp
    }

}