package top.wcpe.corebridge.api.packet

/**
 * 由 WCPE 在 2024/9/26 10:28 创建
 * <p>
 * Created by WCPE on 2024/9/26 10:28
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
@JvmInline
value class ArgumentsBuilder(
    val arguments: MutableList<Argument> = mutableListOf(),
) {
    fun argument(name: String) {
        argument(name, true)
    }

    fun argument(name: String, required: Boolean) {
        arguments.add(Argument(name, required))
    }
}