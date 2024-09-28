package top.wcpe.corebridge.core.packet.extend

import top.wcpe.corebridge.api.packet.Argument
import top.wcpe.corebridge.api.packet.ArgumentsBuilder
import top.wcpe.corebridge.api.packet.PacketExecutor
import top.wcpe.corebridge.core.Message
import top.wcpe.corebridge.core.packet.ChildPacket
import top.wcpe.corebridge.core.packet.ChildPacketBuilder
import top.wcpe.corebridge.core.packet.ParentPacket
import top.wcpe.corebridge.core.packet.ParentPacketBuilder
import top.wcpe.corebridge.core.packet.SinglePacket
import top.wcpe.corebridge.core.packet.SinglePacketBuilder

/**
 * 由 WCPE 在 2024/9/26 10:30 创建
 * <p>
 * Created by WCPE on 2024/9/26 10:30
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
inline fun singlePacket(
    name: String,
    description: String = "",
    arguments: List<Argument> = listOf(),
    usageMessage: String = "",
    packetExecutor: PacketExecutor? = null,
    crossinline runnable: SinglePacket.() -> Unit = {},
): SinglePacket {
    return object : SinglePacket(
        name,
        description,
        arguments,
        usageMessage,
    ) {
        init {
            this.packetExecutor = packetExecutor
        }
    }.also(runnable)
}

inline fun singlePacket(
    singlePacketBuilder: SinglePacketBuilder, crossinline runnable: SinglePacket.() -> Unit = {},
): SinglePacket {
    return singlePacket(
        name = singlePacketBuilder.name,
        description = singlePacketBuilder.description,
        arguments = singlePacketBuilder.arguments,
        usageMessage = singlePacketBuilder.usageMessage,
        packetExecutor = singlePacketBuilder.packetExecutor,
        runnable = runnable
    )
}

inline fun parentPacket(
    name: String,
    description: String = "",
    usageMessage: String = "",
    crossinline runnable: ParentPacket.() -> Unit = {},
): ParentPacket {
    return object : ParentPacket(
        name,
        description,
        usageMessage,
    ) {}.also(runnable)
}

inline fun parentPacket(
    parentPacketBuilder: ParentPacketBuilder, crossinline runnable: ParentPacket.() -> Unit = {},
): ParentPacket {
    return parentPacket(
        name = parentPacketBuilder.name,
        description = parentPacketBuilder.description,
        usageMessage = parentPacketBuilder.usageMessage,
        runnable = runnable
    )
}

inline fun ParentPacket.childPacket(
    name: String,
    description: String = "",
    arguments: List<Argument> = listOf(),
    usageMessage: String = "",
    shouldDisplay: Boolean = false,
    packetExecutor: PacketExecutor? = null,
    crossinline runnable: ChildPacket.() -> Unit = {},
): ChildPacket {
    return childPacket(
        name,
        description,
        arguments,
        usageMessage,
        shouldDisplay,
        packetExecutor,
    ).also(runnable)
}

inline fun ParentPacket.childPacket(
    childPacketBuilder: ChildPacketBuilder, crossinline runnable: ChildPacket.() -> Unit = {},
): ChildPacket {
    return childPacket(
        name = childPacketBuilder.name,
        description = childPacketBuilder.description,
        arguments = childPacketBuilder.arguments,
        usageMessage = childPacketBuilder.usageMessage,
        packetExecutor = childPacketBuilder.packetExecutor,
        runnable = runnable
    )
}

inline fun arguments(crossinline block: ArgumentsBuilder.() -> Unit): List<Argument> {
    val builder = ArgumentsBuilder()
    builder.block()
    return builder.arguments
}

fun List<Argument>.simpleJoinToString(): String {
    return joinToString(" ") {
        if (it.required) {
            Message.RequiredFormat.toLocalization(
                "%argument_name%" to it.name,
                "%argument_description%" to it.description
            )
        } else {
            Message.OptionalFormat.toLocalization(
                "%argument_name%" to it.name,
                "%argument_description%" to it.description
            )
        }
    }
}