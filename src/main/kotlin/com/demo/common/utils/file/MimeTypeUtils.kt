package com.demo.common.utils.file

class MimeTypeUtils {
    companion object{
        const val IMAGE_PNG = "image/png"

        const val IMAGE_JPG = "image/jpg"

        const val IMAGE_JPEG = "image/jpeg"

        const val IMAGE_BMP = "image/bmp"

        const val IMAGE_GIF = "image/gif"

        val IMAGE_EXTENSION = arrayOf("bmp", "gif", "jpg", "jpeg", "png")

        val FLASH_EXTENSION = arrayOf("swf", "flv")

        val MEDIA_EXTENSION = arrayOf("swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg",
                "asf", "rm", "rmvb")

        val DEFAULT_ALLOWED_EXTENSION = arrayOf( // 图片
                "bmp", "gif", "jpg", "jpeg", "png",  // word excel powerpoint
                "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",  // 压缩文件
                "rar", "zip", "gz", "bz2",  // pdf
                "pdf")

        fun getExtension(prefix: String?): String? {
            return when (prefix) {
                IMAGE_PNG -> "png"
                IMAGE_JPG -> "jpg"
                IMAGE_JPEG -> "jpeg"
                IMAGE_BMP -> "bmp"
                IMAGE_GIF -> "gif"
                else -> ""
            }
        }
    }
}