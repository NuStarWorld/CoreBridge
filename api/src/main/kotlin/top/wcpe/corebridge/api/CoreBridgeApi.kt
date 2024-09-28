package top.wcpe.corebridge.api

import top.wcpe.corebridge.api.services.IGuiService
import top.wcpe.corebridge.api.services.IPacketService
import top.wcpe.corebridge.api.services.IPlaceholderService
import top.wcpe.corebridge.api.services.ISlotService

/**
 * 由 WCPE 在 2024/9/26 11:19 创建
 * <p>
 * Created by WCPE on 2024/9/26 11:19
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
interface CoreBridgeApi {

    /**
     * 获取占位符服务
     * @return [IPlaceholderService] 占位符服务
     */
    fun getPlaceholderService(): IPlaceholderService

    /**
     * 获取槽位服务
     * @return [ISlotService] 槽位服务
     */
    fun getSlotService(): ISlotService

    /**
     * 获取 GUI 服务
     * @return [IGuiService] GUI 服务
     */
    fun getGuiService(): IGuiService


    /**
     * 获取数据包服务
     * @return [IPacketService] 数据包服务
     */
    fun getPacketService(): IPacketService
}