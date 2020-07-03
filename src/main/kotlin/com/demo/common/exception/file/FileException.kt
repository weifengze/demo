package com.demo.common.exception.file

import com.demo.common.exception.base.BaseException

open class FileException(code: String?, args: Array<Any>) : BaseException("file", code, args, null) {
    companion object {
        private const val serialVersionUID = 1L
    }
}