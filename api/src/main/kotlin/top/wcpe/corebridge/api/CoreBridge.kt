package top.wcpe.corebridge.api

/**
 * 由 WCPE 在 2024/9/26 11:20 创建
 * <p>
 * Created by WCPE on 2024/9/26 11:20
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object CoreBridge {

    @JvmStatic
    lateinit var api: CoreBridgeApi
        private set
    @JvmStatic
    lateinit var instance: CoreBridgeInstance
        private set

    fun init(coreBridgeInstance: CoreBridgeInstance, coreBridgeApi: CoreBridgeApi) {
        instance = coreBridgeInstance
        api = coreBridgeApi
    }


}