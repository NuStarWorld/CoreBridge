package top.wcpe.corebridge.impl.dragoncore

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import top.wcpe.corebridge.api.CoreBridgeApi
import top.wcpe.corebridge.api.services.IGuiService
import top.wcpe.corebridge.api.services.IPacketService
import top.wcpe.corebridge.api.services.IPlaceholderService
import top.wcpe.corebridge.api.services.IScriptService
import top.wcpe.corebridge.api.services.ISlotService
import top.wcpe.corebridge.impl.dragoncore.listener.PacketListener
import top.wcpe.corebridge.impl.dragoncore.services.GuiService
import top.wcpe.corebridge.impl.dragoncore.services.PacketService
import top.wcpe.corebridge.impl.dragoncore.services.PlaceholderService
import top.wcpe.corebridge.impl.dragoncore.services.ScriptService
import top.wcpe.corebridge.impl.dragoncore.services.SlotService
import java.io.File

/**
 * 由 WCPE 在 2024/9/26 12:10 创建
 * <p>
 * Created by WCPE on 2024/9/26 12:10
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object DragonCoreCoreBridgeApiImpl : CoreBridgeApi {
    @JvmStatic
    lateinit var coreBridge: Plugin
        private set

    @JvmStatic
    lateinit var dragonCore: Plugin
        private set

    @JvmStatic
    lateinit var guiDirFile: File
        private set

    @JvmStatic
    fun init(coreBridge: Plugin, dragonCore: Plugin): CoreBridgeApi {
        this.coreBridge = coreBridge
        this.dragonCore = dragonCore
        guiDirFile = File(dragonCore.dataFolder, "Gui")
        if (!guiDirFile.exists()) {
            guiDirFile.mkdirs()
        }
        Bukkit.getPluginManager().registerEvents(PacketListener, coreBridge)
        return this
    }

    override fun getPlaceholderService(): IPlaceholderService = PlaceholderService
    override fun getSlotService(): ISlotService = SlotService
    override fun getGuiService(): IGuiService = GuiService
    override fun getPacketService(): IPacketService = PacketService
    override fun getScriptService(): IScriptService = ScriptService
}