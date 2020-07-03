package com.demo.common.utils

import com.demo.common.text.StrFormatter

class StringUtils : org.apache.commons.lang3.StringUtils() {
    companion object {
        // 空字符串
        const val NULLSTR: String = ""

        // 下划线
        const val SEPARATOR: Char = '_'

        /**
         * 获取参数不为空值
         *
         * @param value defaultValue 要判断的value
         * @return value 返回值
         */
        fun <T> nvl(value: T, defaultValue: T): T {
            /**
             * 相当于Java中的三目运算
             * ps：return value != null ? value : defaultValue;
             * ?: 判断值是不是 null
             */
            return value ?: defaultValue
        }

        /**
         * * 判断一个Collection是否为空， 包含List，Set，Queue
         *
         * @param coll 要判断的Collection
         * @return true：为空 false：非空
         */
        fun isEmpty(coll: Collection<*>): Boolean {
            return isNull(coll) || coll.isEmpty()
        }

        /**
         * * 判断一个Collection是否非空，包含List，Set，Queue
         *
         * @param coll 要判断的Collection
         * @return true：非空 false：空
         */
        fun isNotEmpty(coll: Collection<*>): Boolean {
            return !isEmpty(coll)
        }

        /**
         * * 判断一个对象数组是否为空
         *
         * @param objects 要判断的对象数组
         * @return true：为空 false：非空
         */
        fun isEmpty(objects: Array<*>): Boolean {
            return isNull(objects) || objects.size == 0
        }

        /**
         * * 判断一个对象数组是否非空
         *
         * @param objects 要判断的对象数组
         * @return true：非空 false：空
         */
        fun isNotEmpty(objects: Array<*>): Boolean {
            return !isEmpty(objects)
        }

        /**
         * * 判断一个Map是否为空
         *
         * @param map 要判断的Map
         * @return true：为空 false：非空
         */
        fun isEmpty(map: Map<*, *>): Boolean {
            return isNull(map) || map.isEmpty()
        }

        /**
         * * 判断一个Map是否为空
         *
         * @param map 要判断的Map
         * @return true：非空 false：空
         */
        fun isNotEmpty(map: Map<*, *>): Boolean {
            return !isEmpty(map)
        }

        /**
         * * 判断一个字符串是否为空串
         *
         * @param str String
         * @return true：为空 false：非空
         */
        fun isEmpty(str: String): Boolean {
            return isNull(str) || NULLSTR.equals(str.trim())
        }

        /**
         * * 判断一个字符串是否为非空串
         *
         * @param str String
         * @return true：非空串 false：空串
         */
        fun isNotEmpty(str: String): Boolean {
            return !isEmpty(str)
        }

        /**
         * * 判断一个对象是否为空
         *
         * @param obj Any? -> java.Object
         * @return true：为空 false：非空
         */
        fun isNull(obj: Any?): Boolean {
            return obj == null
        }

        /**
         * * 判断一个对象是否非空
         *
         * @param obj Any? ->java.Object
         * @return true：非空 false：空
         */
        fun isNotNull(obj: Any?): Boolean {
            return !isNull(obj)
        }

        /**
         * * 判断一个对象是否是数组类型（Java基本型别的数组）
         *
         * @param object 对象
         * @return true：是数组 false：不是数组
         */
        fun isArray(obj: Any): Boolean {
            return isNotNull(obj) && obj.javaClass.isArray
        }

        /**
         * 去空格
         */
        fun trim(str: String?): String? {
            // 空安全
            return str?.trim() ?: ""
        }

        /**
         * 截取字符串
         *
         * @param str   字符串
         * @param start 开始
         * @return 结果
         */
        fun substring(str: String?, start: Int): String? {
            var start = start
            if (str == null) {
                return NULLSTR
            }
            if (start < 0) {
                start += str.length
            }
            if (start < 0) {
                start = 0
            }
            return if (start > str.length) {
                NULLSTR
            } else str.substring(start)
        }

        /**
         * 截取字符串
         *
         * @param str   字符串
         * @param start 开始
         * @param end   结束
         * @return 结果
         */
        fun substring(str: String?, start: Int, end: Int): String? {
            var start = start
            var end = end
            if (str == null) {
                return NULLSTR
            }
            if (end < 0) {
                end = str.length + end
            }
            if (start < 0) {
                start = str.length + start
            }
            if (end > str.length) {
                end = str.length
            }
            if (start > end) {
                return NULLSTR
            }
            if (start < 0) {
                start = 0
            }
            if (end < 0) {
                end = 0
            }
            return str.substring(start, end)
        }

        /**
         * 格式化文本, {} 表示占位符<br></br>
         * 此方法只是简单将占位符 {} 按照顺序替换为参数<br></br>
         * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br></br>
         * 例：<br></br>
         * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br></br>
         * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br></br>
         * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br></br>
         *
         * @param template 文本模板，被替换的部分用 {} 表示
         * @param params   参数值
         * @return 格式化后的文本
         */
        fun format(template: String, vararg params: Any): String? {
            return if (isEmpty(params.toString()) || isEmpty(template)) {
                template
            } else StrFormatter.format(template, params)
        }

        /**
         * 下划线转驼峰命名
         */
        fun toUnderScoreCase(str: String?): String? {
            if (str == null) {
                return null
            }
            val sb = StringBuilder()
            // 前置字符是否大写
            var preCharIsUpperCase = true
            // 当前字符是否大写
            var curreCharIsUpperCase = true
            // 下一字符是否大写
            var nexteCharIsUpperCase = true
            for (i in 0 until str.length) {
                val c = str[i]
                preCharIsUpperCase = if (i > 0) {
                    Character.isUpperCase(str[i - 1])
                } else {
                    false
                }
                curreCharIsUpperCase = Character.isUpperCase(c)
                if (i < str.length - 1) {
                    nexteCharIsUpperCase = Character.isUpperCase(str[i + 1])
                }
                if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                    sb.append(SEPARATOR)
                } else if (i != 0 && !preCharIsUpperCase && curreCharIsUpperCase) {
                    sb.append(SEPARATOR)
                }
                sb.append(Character.toLowerCase(c))
            }
            return sb.toString()
        }

        /**
         * 是否包含字符串
         *
         * @param str  验证字符串
         * @param strs 字符串组
         * @return 包含返回true
         */
        fun inStringIgnoreCase(str: String?, vararg strs: String?): Boolean {
            if (str != null && strs != null) {
                for (s in strs) {
                    if (str.equals(trim(s), ignoreCase = true)) {
                        return true
                    }
                }
            }
            return false
        }

        /**
         * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
         *
         * @param name 转换前的下划线大写方式命名的字符串
         * @return 转换后的驼峰式命名的字符串
         */
        fun convertToCamelCase(name: String): String {
            val result = StringBuilder()
            // 快速检查
            if (name.isEmpty()) {
                // 没必要转换
                return ""
            } else if (!name.contains("_")) {
                // 不含下划线，仅将首字母大写
                return name.substring(0, 1).toUpperCase() + name.substring(1)
            }
            // 用下划线将原始字符串分割
            val camels = name.split("_".toRegex()).toTypedArray()
            for (camel in camels) {
                // 跳过原始字符串中开头、结尾的下换线或双重下划线
                if (camel.isEmpty()) {
                    continue
                }
                // 首字母大写
                result.append(camel.substring(0, 1).toUpperCase())
                result.append(camel.substring(1).toLowerCase())
            }
            return result.toString()
        }

        /**
         * 驼峰式命名法
         * 例如：user_name->userName
         */
        fun toCamelCase(s: String?): String? {
            var s = s ?: return null
            s = s.toLowerCase()
            val sb = StringBuilder(s.length)
            var upperCase = false
            for (i in 0 until s.length) {
                val c = s[i]
                if (c == SEPARATOR) {
                    upperCase = true
                } else if (upperCase) {
                    sb.append(Character.toUpperCase(c))
                    upperCase = false
                } else {
                    sb.append(c)
                }
            }
            return sb.toString()
        }

        fun <T> cast(obj: Any): T {
            return obj as T
        }
    }
}