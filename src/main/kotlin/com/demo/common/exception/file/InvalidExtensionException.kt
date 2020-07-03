package com.demo.common.exception.file

import org.apache.commons.fileupload.FileUploadException
import java.util.*

open class InvalidExtensionException(val allowedExtension: Array<String>, val extension: String, val filename: String) : FileUploadException("filename : [" + filename + "], extension : [" + extension + "], allowed extension : [" + Arrays.toString(allowedExtension) + "]") {

    class InvalidImageExtensionException(allowedExtension: Array<String>, extension: String, filename: String) : InvalidExtensionException(allowedExtension, extension, filename) {
        companion object {
            private const val serialVersionUID = 1L
        }
    }

    class InvalidFlashExtensionException(allowedExtension: Array<String>, extension: String, filename: String) : InvalidExtensionException(allowedExtension, extension, filename) {
        companion object {
            private const val serialVersionUID = 1L
        }
    }

    class InvalidMediaExtensionException(allowedExtension: Array<String>, extension: String, filename: String) : InvalidExtensionException(allowedExtension, extension, filename) {
        companion object {
            private const val serialVersionUID = 1L
        }
    }

    companion object {
        private const val serialVersionUID = 1L
    }

}