package top.wcpe.corebridge.core.packet

import top.wcpe.corebridge.api.packet.Argument
import top.wcpe.corebridge.api.packet.PacketExecutor
import top.wcpe.corebridge.api.packet.PacketSender
import top.wcpe.corebridge.core.Message
import top.wcpe.corebridge.core.packet.extend.simpleJoinToString
import kotlin.collections.get
import kotlin.math.max
import kotlin.to

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
abstract class ParentPacket @JvmOverloads constructor(
    name: String,
    description: String = "",
    usageMessage: String = "",
    parentCommandBuilder: ParentPacketBuilder = ParentPacketBuilder(
        name = name,
        description = description,
        usageMessage = usageMessage,
    ),
) : AbstractPacket(
    name = parentCommandBuilder.name,
    description = parentCommandBuilder.description,
    arguments = emptyList(),
    usageMessage = parentCommandBuilder.usageMessage,
), PacketExecutor {

    private val childCommandMap: MutableMap<String, ChildPacket> = mutableMapOf()

    fun getChildCommandMap(): Map<String, ChildPacket> {
        return childCommandMap.toMap()
    }

    fun addChildPacket(childCommand: ChildPacket) {
        childCommandMap[childCommand.name] = childCommand
    }

    @JvmOverloads
    fun childPacket(
        name: String,
        description: String = "",
        arguments: List<Argument> = listOf(),
        usageMessage: String = "",
        shouldDisplay: Boolean = false,
        packetExecutor: PacketExecutor? = null,
    ): ChildPacket {
        return object : ChildPacket(
            this, name, description, arguments = arguments, usageMessage = usageMessage, shouldDisplay = shouldDisplay
        ) {
            init {
                this.packetExecutor = packetExecutor
            }
        }
    }

    /**
     * 获取重复的字符串
     *
     * @param repeatString 重复的字符串
     * @param length 重复多少次
     * @return String
     */
    open fun getRepeatString(repeatString: String, length: Int): String {
        val stringBuilder = StringBuilder()
        (0 until length).forEach {
            stringBuilder.append(repeatString)
        }
        return stringBuilder.toString()
    }

    private val pageSize = 5

    private fun sendHelper(packetSender: PacketSender<*>, page: Int) {
        val childCommands = childCommandMap.filter { (_, command) ->
            packetSender.isOp() || command.shouldDisplay
        }.map { it.value }

        val totalPages = max(1, (childCommands.size + pageSize - 1) / pageSize)
        val pageNumber = page.coerceIn(1, totalPages)
        val startIndex = (pageNumber - 1) * pageSize
        val endIndex = minOf(startIndex + pageSize, childCommands.size)


        val builder = StringBuilder()
        builder.appendLine(
            Message.PacketHelpTop.toLocalization(
                "%packet_name%" to name, "%page%" to pageNumber, "%total_page%" to totalPages
            )
        )

        val space = getRepeatString(" ", name.length)

        for (command in childCommands.subList(startIndex, endIndex)) {
            builder.appendLine(
                Message.PacketHelpFormat.toLocalization(
                    "%space%" to space,
                    "%packet_name%" to command.name,
                    "%arguments%" to command.arguments.simpleJoinToString(),
                    "%description%" to command.description
                )
            )
        }

        builder.appendLine(
            Message.PacketHelpBottom.toLocalization(
                "%packet_name%" to name, "%argument_tip%" to Message.ArgumentTip.toLocalization()
            )
        )

        packetSender.sendMessage(builder.toString())
    }

    override fun execute(packetSender: PacketSender<*>, args: Array<String?>) {
        if (args.isEmpty() || args[0] == "help") {
            val pageNumber = args.getOrNull(1)?.toIntOrNull() ?: 1
            sendHelper(packetSender, pageNumber)
            return
        }
        val commandName = args[0]
        val childCommand = childCommandMap[commandName]
        if (childCommand == null) {
            packetSender.sendMessage(Message.PacketNotExist.toLocalization("%packet_name%" to name))
            return
        }
        childCommand.handleExecute(packetSender, args.copyOfRange(1, args.size))
    }

}