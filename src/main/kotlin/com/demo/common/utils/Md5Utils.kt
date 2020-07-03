package com.demo.common.utils

import org.slf4j.LoggerFactory
import java.security.MessageDigest
import kotlin.experimental.and

class Md5Utils {
    companion object {
        private val log = LoggerFactory.getLogger(Md5Utils::class.java)
        private fun md5(s: String): ByteArray? {
            val algorithm: MessageDigest
            try {
                algorithm = MessageDigest.getInstance("MD5")
                algorithm.reset()
                algorithm.update(s.toByteArray(charset("UTF-8")))
                return algorithm.digest()
            } catch (e: Exception) {
                log.error("MD5 Error...", e)
            }
            return null
        }

        private fun toHex(hash: ByteArray?): String? {
            if (hash == null) {
                return null
            }
            val buf = StringBuffer(hash.size * 2)
            var i: Int
            i = 0
            while (i < hash.size) {
                if (hash[i] and 0xff.toByte() < 0x10) {
                    buf.append("0")
                }
                buf.append(java.lang.Long.toString((hash[i] and 0xff.toLong().toByte()).toLong(), 16))
                i++
            }
            return buf.toString()
        }

        fun hash(s: String): String? {
            return try {
                String(toHex(md5(s))!!.toByteArray(charset("UTF-8")), Charsets.UTF_8)
            } catch (e: Exception) {
                log.error("not supported charset...{}", e)
                s
            }
        }
    }
}