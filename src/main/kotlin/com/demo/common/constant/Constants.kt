package com.demo.common.constant

import java.nio.charset.Charset

class Constants {
    companion object {
        /**
         * UTF-8 字符集
         */
        const val UTF8: String = "UTF-8"


        /**
         * GBK 字符集
         */
        const val GBK: String = "GBK"

        /**
         * 通用成功标识
         */
        const val SUCCESS: String = "0"

        /**
         * 通用失败标识
         */
        const val FAIL: String = "1"

        /**
         * 登录成功
         */
        const val LOGIN_SUCCESS: String = "Success"

        /**
         * 注销
         */
        const val LOGOUT: String = "Logout"

        /**
         * 注册
         */
        const val REGISTER: String = "Register"

        /**
         * 登录失败
         */
        const val LOGIN_FAIL: String = "Error"

        /**
         * 当前记录起始索引
         */
        const val PAGE_NUM: String = "pageNum"

        /**
         * 每页显示记录数
         */
        const val PAGE_SIZE: String = "pageSize"

        /**
         * 排序列
         */
        const val ORDER_BY_COLUMN: String = "orderByColumn"

        /**
         * 排序的方向 "desc" 或者 "asc".
         */
        const val IS_ASC: String = "isAsc"

        /**
         * 参数管理 cache name
         */
        const val SYS_CONFIG_CACHE: String = "sys-config"

        /**
         * 参数管理 cache key
         */
        const val SYS_CONFIG_KEY: String = "sys_config:"

        /**
         * 字典管理 cache name
         */
        const val SYS_DICT_CACHE: String = "sys-dict"

        /**
         * 字典管理 cache key
         */
        const val SYS_DICT_KEY: String = "sys_dict:"

        /**
         * 资源映射路径 前缀
         */
        const val RESOURCE_PREFIX: String = "/profile"
    }
}