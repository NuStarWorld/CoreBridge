package top.wcpe.corebridge.core.packet

import top.wcpe.corebridge.api.packet.Argument
import top.wcpe.corebridge.api.packet.PacketExecutor
import top.wcpe.corebridge.core.Message
import top.wcpe.corebridge.core.packet.extend.simpleJoinToString
import top.wcpe.corebridge.core.packet.extend.singlePacket

/**
 * 由 WCPE 在 2024/9/26 10:27 创建
 * <p>
 * Created by WCPE on 2024/9/26 10:27
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
class SinglePacketBuilder @JvmOverloads constructor(
    val name: String,
    val description: String = "",
    val arguments: List<Argument>,
    usageMessage: String = "",
    val packetExecutor: PacketExecutor? = null,
) {

    val usageMessage: String = usageMessage
        get() {
            return field.ifEmpty {
                Message.UsageMessageFormat.toLocalization(
                    "%packet_name%" to name, "%arguments%" to arguments.simpleJoinToString(), "%argument_tip%" to Message.ArgumentTip.toLocalization()
                )
            }
        }


    fun build(): SinglePacket {
        return singlePacket(this)
    }
}