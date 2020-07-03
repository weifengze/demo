package com.demo.common.utils

import com.alibaba.fastjson.JSONObject
import com.demo.common.constant.Constants
import com.demo.common.utils.http.HttpUtils
import org.slf4j.LoggerFactory

class AddressUtils {
    companion object {
        private val log = LoggerFactory.getLogger(AddressUtils::class.java)

        // IP地址查询
        private const val IP_URL = "http://whois.pconline.com.cn/ipJson.jsp"

        // 未知地址
        private const val UNKNOWN = "XX XX"

        fun getRealAddressByIP(ip: String): String {
            val address = UNKNOWN
            // 内网不查询
            if (IpUtils.internalIp(ip)) {
                return "内网IP"
            }
            try {
                val rspStr: String = HttpUtils.sendGet(IP_URL, "ip=$ip&json=true", Constants.GBK)
                if (StringUtils.isEmpty(rspStr)) {
                    log.error("获取地理位置异常 {}", ip)
                    return UNKNOWN
                }
                val obj = JSONObject.parseObject(rspStr)
                val region = obj.getString("pro")
                val city = obj.getString("city")
                return String.format("%s %s", region, city)
            } catch (e: Exception) {
                log.error("获取地理位置异常 {}", e)
            }
            return address
        }

        @JvmStatic
        fun main(args: Array<String>) {
            log.info("检查IP")
            val ip = "111.202.206.197"
            println(getRealAddressByIP(ip))
        }
    }
}
