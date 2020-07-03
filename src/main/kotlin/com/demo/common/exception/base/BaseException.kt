package com.demo.common.exception.base

import com.demo.common.utils.MessageUtils
import org.springframework.util.StringUtils

open class BaseException(s: String, code: String?, args: Array<Any>, nothing: Nothing?) : RuntimeException() {
    private val serialVersionUID = 1L

    /**
     * 所属模块
     */
    private var module: String? = null

    /**
     * 错误码
     */
    private var code: String? = null

    /**
     * 错误码对应的参数
     * lateinit 变量需要在定义后才赋值
     */
    private lateinit var args: Array<Any?>

    /**
     * 错误消息
     */
    private var defaultMessage: String? = null

    fun BaseException(module: String?, code: String?, args: Array<Any?>?, defaultMessage: String?) {
        this.module = module
        this.code = code
        this.args = arrayOf(args)
        this.defaultMessage = defaultMessage
    }

    fun BaseException(module: String?, code: String?, args: Array<Any?>?) {
        this.BaseException(module, code, args, null)
    }


    fun BaseException(module: String?, defaultMessage: String?) {
        this.BaseException(module, null, null, defaultMessage)
    }

    fun BaseException(code: String?, args: Array<Any?>?) {
        this.BaseException(null, code, args, null)
    }

    fun BaseException(defaultMessage: String?) {
        this.BaseException(null, null, null, defaultMessage)
    }


    fun getMessages(): String? {
        var message: String? = null
        if (!StringUtils.isEmpty(code)) {
            message = MessageUtils.message(code, args)
        }
        if (message == null) {
            message = defaultMessage
        }
        return message
    }

    fun getModule(): String? {
        return module
    }

    fun getCode(): String? {
        return code
    }

    fun getArgs(): Array<Any?>? {
        return args
    }

    fun getDefaultMessage(): String? {
        return defaultMessage
    }

    override fun toString(): String {
        return this.javaClass.toString() + "{" + "module='" + module + '\'' + ", message='" + message + '\'' + '}'
    }
}