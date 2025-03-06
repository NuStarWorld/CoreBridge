## CoreBridge

各个 UI 平台的桥接插件

### 依赖

```kotlin
repositories {
    maven("https://maven.wcpe.top/repository/maven-public/")
}

dependencies {
    compileOnly("top.wcpe.corebridge:corebridge-api:1.0.0")
}
```

## 使用方式

## 在服务器处理客户端发送的数据

一般是两个插件都是使用 Bukkit 的事件系统进行监听处理，本插件进行封装了一下可以直接使用注解注册处理器

使用注解创建一个接受 DragonCore 的 `方法.发包` , GermPlugin 的 `DOS<->xxxx` 的处理器

使用 `@ParentPacket` 标注父类 注解有以下参数

```kotlin
/**
 * 父发包名称
 */
val name: String

/**
 * 描述（选填）
 * 默认 空
 */
val description: String

/**
 * 使用帮助（选填）
 * 默认 空
 */
val usageMessage: String
```

使用 `@ChildPacket` 标注子类

```kotlin
/**
 * 子发包名称
 */
val name: String

/**
 * 描述（选填）
 * 默认 空
 */
val description: String

/**
 * 参数（选填）
 * 默认 空
 */
val arguments: Array<Argument>

/**
 * 使用帮助（选填）
 * 默认 空
 */
val usageMessage: String

/**
 * 是否需要显示在帮助列表中（选填）
 * 默认 false
 */
val shouldDisplay: Boolean 
```

```kotlin
@ParentPacket("ExamplePacket")
abstract class ExamplePacket : PacketExecutor {

    /**
     * 无参数发包处理器
     *
     * 以下各个平台对应处理
     * DragonCore: 方法.发包('ExamplePacket', 'simpleExample1');
     * GermPlugin: ExamplePacket<->simpleExample1
     */
    @ChildPacket(
        name = "simpleExample1",
        description = "简单示例1"
    )
    class RefreshExamplePacketPlaceholderChildPacket : ExamplePacketsPacket() {
        override fun execute(packetSender: PacketSender<*>, args: Array<String?>) {
            val player = packetSender.getPacketSender() as Player
            //在这里处理逻辑
        }
    }

    /**
     * 有参发包处理器
     *
     * 以下各个平台对应处理
     * DragonCore: 方法.发包('ExamplePacket', 'simpleExample2', '参数1');
     * GermPlugin: ExamplePacket<->simpleExample2 参数1
     */
    @ChildPacket(
        name = "simpleExample2",
        description = "简单示例2",
        arguments = [
            Argument("arg1", true, "参数1"),
        ]
    )
    class RefreshExamplePacketPlaceholderChildPacket : ExamplePacketsPacket() {
        override fun execute(packetSender: PacketSender<*>, args: Array<String?>) {
            val player = packetSender.getPacketSender() as Player
            val arg1 = args[0] ?: return

            //在这里处理逻辑
        }
    }
}
```

以上这段需要注册使用

```kotlin
CoreBridge.instance.registerPacket(ExamplePacket::class.java, 注册的插件主类实例)
```

### 发送变量给客户端

使用 `CoreBridge.api.getPlaceholderService()` 获取服务，其中有以下方法

```kotlin
   /**
 * 发送单个占位符
 * @param player 玩家
 * @param placeholder 占位符
 * @param value 值
 */
fun sendPlaceholder(player: Player, placeholder: String, value: String)

/**
 * 发送多个占位符
 * @param player 玩家
 * @param placeholderMap 占位符映射
 * @param placeholderMap 值
 */
fun sendPlaceholderMap(player: Player, placeholderMap: Map<String, String>)

/**
 * 发送多个占位符
 * @param player 玩家
 * @param placeholders 占位符列表
 */
fun sendPlaceholders(player: Player, vararg placeholders: Pair<String, String>)


/**
 * 删除单个占位符
 * @param player 玩家
 * @param placeholder 占位符
 */
fun removePlaceholder(player: Player, placeholder: String, startsWith: Boolean = false)

/**
 * 删除多个占位符
 * @param player 玩家
 * @param placeholders 占位符列表
 */
fun removePlaceholders(player: Player, vararg placeholders: String)
```

### 使用插件生成对应的文档

如果使用注解创建了发包处理器并且使用的是 Gradle 还有对应的插件可以生成文档方便对接

https://github.com/wcpe/CoreBridgeGradlePlugin

生成文档示例:

> 文件 ExamplePacket_DragonCore.md

ExamplePacket 发包

| 发包                                                    |  描述   | 
|:------------------------------------------------------|:-----:|
| 方法.发包('ExamplePacket', 'simpleExample1')              | 简单示例1 |
| 方法.发包('ExamplePacket', 'simpleExample2', 'arg1{参数1}') | 简单示例2 |

> 文件 ExamplePacket_GermPlugin.md

ExamplePacket DOS

| DOS                                      |  描述   | 
|:-----------------------------------------|:-----:|
| ExamplePacket<->simpleExample1           | 简单示例1 |
| ExamplePacket<->simpleExample2 arg1{参数1} | 简单示例2 |