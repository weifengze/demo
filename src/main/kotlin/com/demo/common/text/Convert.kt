package com.demo.common.text

import com.demo.common.constant.Constants
import java.nio.ByteBuffer
import java.nio.charset.Charset

class Convert {
    companion object {
        /**
         * 将对象转为字符串<br></br>
         * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组 2、对象数组会调用Arrays.toString方法
         *
         * @param obj     对象
         * @param charset 字符集
         * @return 字符串
         */
        fun str(obj: Any?, charset: Charset?): String? {
            if (null == obj) {
                return null
            }
            if (obj is String) {
                return obj
            } else if (obj is ByteArray || obj is Array<*>) {
                return str(obj as Array<*>?, charset)
            } else if (obj is ByteBuffer) {
                return str(obj as ByteBuffer?, charset)
            }
            return obj.toString()
        }

        /**
         * 将对象转为字符串<br></br>
         * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组 2、对象数组会调用Arrays.toString方法
         *
         * @param obj 对象
         * @return 字符串
         */
        fun utf8Str(obj: Any?): String? {
            return str(obj, Charsets.UTF_8)
        }

    }
}