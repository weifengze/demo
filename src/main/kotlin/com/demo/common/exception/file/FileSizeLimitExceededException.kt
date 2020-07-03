package com.demo.common.exception.file

class FileSizeLimitExceededException(defaultMaxSize: Long) : FileException("upload.exceed.maxSize", arrayOf(defaultMaxSize)) {
    companion object {
        private const val serialVersionUID = 1L
    }
}