package com.demo.common.utils.file

import java.io.*
import java.net.URLEncoder
import javax.servlet.http.HttpServletRequest

class FileUtils {
    companion object {
        val FILENAME_PATTERN = Regex("[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+")

        /**
         * 输出指定文件的byte数组
         *
         * @param filePath 文件路径
         * @param os 输出流
         * @return
         */
        @Throws(IOException::class)
        fun writeBytes(filePath: String?, os: OutputStream?) {
            var fis: FileInputStream? = null
            try {
                val file = File(filePath)
                if (!file.exists()) {
                    throw FileNotFoundException(filePath)
                }
                fis = FileInputStream(file)
                val b = ByteArray(1024)
                var length: Int
                while (fis.read(b).also { length = it } > 0) {
                    os!!.write(b, 0, length)
                }
            } catch (e: IOException) {
                throw e
            } finally {
                if (os != null) {
                    try {
                        os.close()
                    } catch (e1: IOException) {
                        e1.printStackTrace()
                    }
                }
                if (fis != null) {
                    try {
                        fis.close()
                    } catch (e1: IOException) {
                        e1.printStackTrace()
                    }
                }
            }
        }

        /**
         * 删除文件
         *
         * @param filePath 文件
         * @return
         */
        fun deleteFile(filePath: String): Boolean {
            var flag = false
            val file = File(filePath)
            // 路径为文件且不为空则进行删除
            if (file.isFile && file.exists()) {
                file.delete()
                flag = true
            }
            return flag
        }

        /**
         * 文件名称验证
         *
         * @param filename 文件名称
         * @return true 正常 false 非法
         */
        fun isValidFilename(filename: String): Boolean {
            return filename.matches(FILENAME_PATTERN)
        }

        /**
         * 下载文件名重新编码
         *
         * @param request 请求对象
         * @param fileName 文件名
         * @return 编码后的文件名
         */
        @Throws(UnsupportedEncodingException::class)
        fun setFileDownloadHeader(request: HttpServletRequest, fileName: String): String? {
            val agent = request.getHeader("USER-AGENT")
            var filename = fileName
            if (agent.contains("MSIE")) {
                // IE浏览器
                filename = URLEncoder.encode(filename, "utf-8")
                filename = filename.replace("+", " ")
            } else if (agent.contains("Firefox")) {
                // 火狐浏览器
                filename = String(fileName.toByteArray(), Charsets.ISO_8859_1)
            } else if (agent.contains("Chrome")) {
                // google浏览器
                filename = URLEncoder.encode(filename, "utf-8")
            } else {
                // 其它浏览器
                filename = URLEncoder.encode(filename, "utf-8")
            }
            return filename
        }
    }
}