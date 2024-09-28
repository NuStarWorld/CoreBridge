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
data class Argument(
    val name: String, val required: Boolean = false, val description: String = name,
)