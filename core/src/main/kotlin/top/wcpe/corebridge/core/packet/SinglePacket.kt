package top.wcpe.corebridge.core.packet

import top.wcpe.corebridge.api.packet.Argument

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
abstract class SinglePacket @JvmOverloads constructor(
    name: String,
    description: String,
    arguments: List<Argument> = listOf(),
    usageMessage: String = "",
    singlePacketBuilder: SinglePacketBuilder = SinglePacketBuilder(
        name,
        description,
        arguments,
        usageMessage,
    ),
) : AbstractPacket(
    name = singlePacketBuilder.name,
    description = singlePacketBuilder.description,
    arguments = singlePacketBuilder.arguments,
    usageMessage = singlePacketBuilder.usageMessage
) {
    constructor(
        singlePacketBuilder: SinglePacketBuilder,
    ) : this(
        name = singlePacketBuilder.name,
        description = singlePacketBuilder.description,
        arguments = singlePacketBuilder.arguments,
        usageMessage = singlePacketBuilder.usageMessage
    )


}
