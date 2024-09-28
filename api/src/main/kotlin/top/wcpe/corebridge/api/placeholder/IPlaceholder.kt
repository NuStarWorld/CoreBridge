package top.wcpe.corebridge.api.placeholder

import java.util.function.Consumer

/**
 * 由 WCPE 在 2024/9/27 14:48 创建
 * <p>
 * Created by WCPE on 2024/9/27 14:48
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
interface IPlaceholder {

    /**
     * 格式化占位符
     */
    val formats: List<Format>

    /**
     * 占位符的描述
     */
    val description: String

    /**
     * 占位符的结果值
     */
    val resultValue: String


    /**
     * 处理所有的格式并返回
     * @param placeholderPairs 包含占位符及其对应值的映射。
     * @return 处理后的格式列表。
     */
    fun processAllFormats(
        vararg placeholderPairs: Pair<String, String>,
    ): List<String> {
        val placeholderMap = placeholderPairs.toMap()
        return processAllFormats(placeholderMap)
    }

    /**
     * 处理所有的格式并返回
     *
     * @param placeholderMap 包含占位符及其对应值的映射。
     */
    fun processAllFormats(
        placeholderMap: Map<String, String>,
    ): List<String> {
        return formats.map { format ->
            format.processFormat(placeholderMap)
        }
    }

    /**
     * 消费所有的格式。
     * @param placeholderPairs 包含占位符及其对应值的映射。
     * @param formatConsumer 用于处理单个格式字符串的消费者函数。
     */
    fun consumeAllFormats(
        vararg placeholderPairs: Pair<String, String>,
        formatConsumer: Consumer<String>,
    ) {
        val placeholderMap = placeholderPairs.toMap()
        consumeAllFormats(placeholderMap, formatConsumer)
    }


    /**
     * 消费所有格式。
     *
     * @param placeholderMap 包含占位符及其对应值的映射。
     * @param formatConsumer 用于处理单个格式字符串的消费者函数。
     */
    fun consumeAllFormats(
        placeholderMap: Map<String, String>,
        formatConsumer: Consumer<String>,
    ) {
        formats.forEach { format ->
            format.consumeFormat(placeholderMap, formatConsumer)
        }
    }
}