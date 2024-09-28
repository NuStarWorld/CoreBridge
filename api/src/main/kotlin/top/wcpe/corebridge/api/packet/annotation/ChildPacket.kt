package top.wcpe.corebridge.api.packet.annotation

/**
 * 由 WCPE 在 2024/9/26 10:21 创建
 * <p>
 * Created by WCPE on 2024/9/26 10:21
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ChildPacket(
    val name: String,
    val description: String = "",
    val arguments: Array<Argument> = [],
    val usageMessage: String = "",
    val shouldDisplay: Boolean = false,
)