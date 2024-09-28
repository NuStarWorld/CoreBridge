package top.wcpe.corebridge.api.packet.annotation

/**
 * 由 WCPE 在 2024/9/26 10:20 创建
 * <p>
 * Created by WCPE on 2024/9/26 10:20
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
@Retention(AnnotationRetention.RUNTIME)
annotation class Argument(
    val name: String, val required: Boolean = true, val description: String = "",
)