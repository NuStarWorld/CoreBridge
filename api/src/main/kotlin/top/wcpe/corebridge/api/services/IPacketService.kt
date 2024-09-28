package top.wcpe.corebridge.api.services

/**
 * 由 WCPE 在 2024/9/27 11:08 创建
 * <p>
 * Created by WCPE on 2024/9/27 11:08
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
interface IPacketService {
    /**
     * 添加白名单数据包
     * @param identifier 包的唯一标识符
     */
    fun addWhitelistPacket(identifier: String)
}