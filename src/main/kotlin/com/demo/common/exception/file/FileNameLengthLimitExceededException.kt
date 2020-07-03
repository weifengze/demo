package com.demo.common.exception.file

class FileNameLengthLimitExceededException(defaultFileNameLength: Int) : FileException("upload.filename.exceed.length", arrayOf<Any>(defaultFileNameLength)) {
    companion object {
        private const val serialVersionUID = 1L
    }
}
