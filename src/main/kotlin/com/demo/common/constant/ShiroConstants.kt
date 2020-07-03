package com.demo.common.constant

/**
 * Shiro通用常量
 */
class ShiroConstants {
    companion object {
        /**
         * 当前登录的用户
         */
        const val CURRENT_USER: String = "currentUser"

        /**
         * 用户名
         */
        const val CURRENT_USERNAME: String = "username"

        /**
         * 消息key
         */
        const val MESSAGE: String = "message"

        /**
         * 错误key
         */
        const val ERROR: String = "errorMsg"

        /**
         * 编码格式
         */
        const val ENCODING: String = "UTF-8"

        /**
         * 当前在线会话
         */
        const val ONLINE_SESSION: String = "online_session"

        /**
         * 验证码key
         */
        const val CURRENT_CAPTCHA: String = "captcha"

        /**
         * 验证码开关
         */
        const val CURRENT_ENABLED: String = "captchaEnabled"

        /**
         * 验证码类型
         */
        const val CURRENT_TYPE: String = "captchaType"

        /**
         * 验证码
         */
        const val CURRENT_VALIDATECODE: String = "validateCode"

        /**
         * 验证码错误
         */
        const val CAPTCHA_ERROR: String = "captchaError"

        /**
         * 登录记录缓存
         */
        const val LOGINRECORDCACHE: String = "loginRecordCache"

        /**
         * 系统活跃用户缓存
         */
        const val SYS_USERCACHE: String = "sys-userCache"
    }
}