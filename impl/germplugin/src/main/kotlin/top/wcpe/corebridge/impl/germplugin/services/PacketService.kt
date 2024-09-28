package top.wcpe.corebridge.impl.germplugin.services

import com.germ.germplugin.api.GermDosAPI
import top.wcpe.corebridge.api.services.IPacketService

/**
 * 由 WCPE 在 2024/9/27 11:15 创建
 * <p>
 * Created by WCPE on 2024/9/27 11:15
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object PacketService : IPacketService {
    override fun addWhitelistPacket(identifier: String) {
        GermDosAPI.registerDos(identifier)
    }
}