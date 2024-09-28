package top.wcpe.corebridge.core.packet

import top.wcpe.corebridge.api.packet.Argument

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
abstract class ChildPacket @JvmOverloads constructor(
    parentPacket: ParentPacket,
    name: String,
    description: String,
    arguments: List<Argument> = listOf(),
    usageMessage: String = "",
    childCommandBuilder: ChildPacketBuilder = ChildPacketBuilder(
        parentPacket,
        name,
        description,
        arguments,
        usageMessage,
    ),
    val shouldDisplay: Boolean = false,
) : AbstractPacket(
    name = childCommandBuilder.name,
    description = childCommandBuilder.description,
    arguments = childCommandBuilder.arguments,
    usageMessage = childCommandBuilder.usageMessage,
)