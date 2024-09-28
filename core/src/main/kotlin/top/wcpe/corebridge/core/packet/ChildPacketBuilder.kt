package top.wcpe.corebridge.core.packet

import top.wcpe.corebridge.api.packet.Argument
import top.wcpe.corebridge.api.packet.PacketExecutor
import top.wcpe.corebridge.core.Message
import top.wcpe.corebridge.core.packet.extend.childPacket
import top.wcpe.corebridge.core.packet.extend.simpleJoinToString

/**
 * 由 WCPE 在 2024/9/26 10:29 创建
 * <p>
 * Created by WCPE on 2024/9/26 10:29
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
class ChildPacketBuilder @JvmOverloads constructor(
    private val parentPacket: ParentPacket,
    val name: String,
    description: String = "",
    val arguments: List<Argument> = listOf(),
    usageMessage: String = "",
    val packetExecutor: PacketExecutor? = null,
) {

    val description: String = description
        get() {
            return field.ifEmpty {
                "$name Packets"
            }
        }


    val usageMessage: String = usageMessage
        get() {
            return field.ifEmpty {
                Message.UsageMessageFormat.toLocalization(
                    "%packet_name%" to parentPacket.name,
                    "%arguments%" to "$description ${arguments.simpleJoinToString()}",
                    "%argument_tip%" to Message.ArgumentTip.toLocalization()
                )
            }
        }

    fun build(): ChildPacket {
        return parentPacket.childPacket(this)
    }


}