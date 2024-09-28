package top.wcpe.corebridge.api.placeholder

import java.util.function.Consumer

/**
 * 由 WCPE 在 2024/9/27 16:35 创建
 * <p>
 * Created by WCPE on 2024/9/27 16:35
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
data class Format(
    /**
     * 格式
     */
    val format: String,
    /**
     * 格式中的占位符
     */
    val placeholders: List<Placeholder> = emptyList(),
    /**
     * 是否弃用
     */
    val deprecated: Boolean = false,
) {
    /**
     * 格式构建器
     */
    class Builder {
        /**
         * 格式列表
         */
        private val formats = mutableListOf<Format>()

        /**
         * 添加一个格式
         */
        fun format(
            formatStr: String,
            placeholders: List<Placeholder> = emptyList(),
            deprecated: Boolean = false,
        ): Builder {
            formats.add(Format(formatStr, placeholders, deprecated))
            return this
        }

        /**
         * 构建格式列表
         */
        fun build(): List<Format> {
            return formats.toList()
        }
    }

    data class Placeholder(
        /**
         * 占位符的索引
         */
        val index: String,
        /**
         * 占位符的显示名称
         */
        val summary: String,
        /**
         * 占位符的描述
         */
        val description: String,
    ) {
        /**
         * 占位符构建器
         */
        class Builder {
            /**
             * 占位符列表
             */
            private val placeholders = mutableListOf<Placeholder>()

            /**
             * 添加一个占位符
             */
            fun placeholder(index: String, summary: String, description: String): Builder {
                placeholders.add(Placeholder(index, summary, description))
                return this
            }

            /**
             * 构建占位符列表
             */
            fun build(): List<Placeholder> {
                return placeholders.toList()
            }
        }
    }


    /**
     * 处理格式
     * @param placeholderPairs 占位符对
     * @return 处理后的格式
     */
    fun processFormat(vararg placeholderPairs: Pair<String, String>): String {
        val formatPlaceholderMap = placeholderPairs.toMap()
        return processFormat(formatPlaceholderMap)
    }

    /**
     * 处理格式
     * @param formatPlaceholderMap 格式占位符映射
     * @return 处理后的格式
     */
    fun processFormat(formatPlaceholderMap: Map<String, String>): String {
        var result = format
        placeholders.forEach { placeholder ->
            val value = formatPlaceholderMap[placeholder.index] ?: return@forEach
            result = result.replace("{${placeholder.index}}", value)
        }
        return result
    }


    /**
     * 消费格式
     * @param placeholderPairs 占位符对
     * @param consumer 消费者
     */
    fun consumeFormat(vararg placeholderPairs: Pair<String, String>, consumer: Consumer<String>) {
        val formatPlaceholderMap = placeholderPairs.toMap()
        val processedFormat = processFormat(formatPlaceholderMap)
        consumer.accept(processedFormat)
    }

    /**
     * 消费格式
     * @param formatPlaceholderMap 格式占位符映射
     * @param consumer 消费者
     */
    fun consumeFormat(formatPlaceholderMap: Map<String, String>, consumer: Consumer<String>) {
        val processedFormat = processFormat(formatPlaceholderMap)
        consumer.accept(processedFormat)
    }
}