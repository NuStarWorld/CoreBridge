package top.wcpe.corebridge.impl.easycore

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import top.wcpe.corebridge.api.CoreBridgeApi
import top.wcpe.corebridge.api.services.IGuiService
import top.wcpe.corebridge.api.services.IPacketService
import top.wcpe.corebridge.api.services.IPlaceholderService
import top.wcpe.corebridge.api.services.ISlotService
import top.wcpe.corebridge.impl.easycore.listener.PacketListener
import top.wcpe.corebridge.impl.easycore.services.GuiService
import top.wcpe.corebridge.impl.easycore.services.PacketService
import top.wcpe.corebridge.impl.easycore.services.PlaceholderService
import top.wcpe.corebridge.impl.easycore.services.SlotService

/**
 * 由 WCPE 在 2024/9/26 12:26 创建
 * <p>
 * Created by WCPE on 2024/9/26 12:26
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object EasyCoreCoreBridgeApiImpl : CoreBridgeApi {
    @JvmStatic
    lateinit var plugin: Plugin
        private set

    @JvmStatic
    fun init(plugin: Plugin): CoreBridgeApi {
        this.plugin = plugin
        Bukkit.getPluginManager().registerEvents(PacketListener, plugin)
        return this
    }

    override fun getPlaceholderService(): IPlaceholderService = PlaceholderService
    override fun getSlotService(): ISlotService = SlotService
    override fun getGuiService(): IGuiService = GuiService
    override fun getPacketService(): IPacketService = PacketService
}