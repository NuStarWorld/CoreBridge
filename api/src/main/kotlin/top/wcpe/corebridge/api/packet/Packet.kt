package top.wcpe.corebridge.api.packet


/**
 * 由 WCPE 在 2024/9/26 14:12 创建
 * <p>
 * Created by WCPE on 2024/9/26 14:12
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
interface Packet {
    val name: String
    val description: String
    val arguments: List<Argument>
    val usageMessage: String
}