package top.wcpe.corebridge.core.packet

import top.wcpe.corebridge.api.packet.Argument
import top.wcpe.corebridge.api.packet.Packet
import top.wcpe.corebridge.api.packet.PacketExecutor
import top.wcpe.corebridge.api.packet.PacketSender
import top.wcpe.corebridge.core.Message
import kotlin.math.max

/**
 * 由 WCPE 在 2024/9/26 10:25 创建
 * <p>
 * Created by WCPE on 2024/9/26 10:25
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
abstract class AbstractPacket(
    override val name: String,
    override val description: String,
    override val arguments: List<Argument>,
    override val usageMessage: String,
) : Packet {

    var packetExecutor: PacketExecutor? = null

    infix fun String.to(required: Boolean): Argument = Argument(this, required)

    fun register(instance: Any): Boolean {
        if (this is ChildPacket) {
            return false
        }
        return PacketManager.registerPacket(this, instance)
    }

    private fun requiredArgs(
        argsStrings: Array<String?>, arguments: List<Argument>,
    ): List<Argument> {
        val result = mutableListOf<Argument>()
        for ((i, value) in arguments.withIndex()) {
            if (value.required && argsStrings[i] == null) {
                result.add(value)
            }
        }
        return result
    }

    private fun notRequiredArgsReplace(argsStrings: Array<String?>, arguments: List<Argument>) {
        for ((i, argument) in arguments.withIndex()) {
            if (argument.required) {
                continue
            }
            val arg = argsStrings[i]
            if (arg == null || arg == " " || arg == ".") {
                argsStrings[i] = null
            }
        }
    }

    fun handleExecute(packetSender: PacketSender<*>, argsList: List<String>) {
        val argsStrings = Array(max(argsList.size, arguments.size)) { index ->
            if (index < argsList.size) argsList[index]
            else null
        }
        handleExecute0(packetSender, argsStrings)
    }

    fun handleExecute(packetSender: PacketSender<*>, args: Array<String?>) {
        val argsStrings = args.copyOf(max(args.size, arguments.size))
        handleExecute0(packetSender, argsStrings)
    }

    private fun handleExecute0(packetSender: PacketSender<*>, argsStrings: Array<String?>) {
        if (beforeExecute(packetSender, argsStrings)) {
            notRequiredArgsReplace(argsStrings, arguments)
            if (this is PacketExecutor) {
                execute(packetSender, argsStrings)
            } else {
                packetExecutor?.execute(packetSender, argsStrings)
            }
        }
    }

    /**
     *
     * 执行 execute 之前执行
     *
     * @param packetSender 执行的对象
     * @param args 参数
     * @return true 检查通过
     * @return false 检查不通过
     */
    open fun beforeExecute(packetSender: PacketSender<*>, args: Array<String?>): Boolean {
        if (arguments.isEmpty()) {
            return true
        }
        val requiredArgResult = requiredArgs(args, arguments)
        if (requiredArgResult.isNotEmpty()) {
            packetSender.sendMessage(usageMessage)
            packetSender.sendMessage(
                Message.PacketArgsError.toLocalization("%arguments%" to requiredArgResult.joinToString(" ") {
                    Message.RequiredFormat.toLocalization(
                        "%argument_name%" to it.name,
                        "%argument_description%" to it.description
                    )
                })
            )
            return false
        }
        return true
    }


}
