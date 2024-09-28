package top.wcpe.corebridge.api.placeholder.extend

import top.wcpe.corebridge.api.placeholder.Format

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
/**
 * 格式扩展
 */
fun formats(block: Format.Builder.() -> Unit): List<Format> {
    val builder = Format.Builder()
    builder.block()
    return builder.build()
}

/**
 * 格式内占位符扩展
 */
fun placeholders(block: Format.Placeholder.Builder.() -> Unit): List<Format.Placeholder> {
    val builder = Format.Placeholder.Builder()
    builder.block()
    return builder.build()
}
