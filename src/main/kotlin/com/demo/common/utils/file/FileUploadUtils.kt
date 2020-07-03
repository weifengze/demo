package com.demo.common.utils.file

import com.demo.common.constant.Constants
import com.demo.common.exception.file.FileNameLengthLimitExceededException
import com.demo.common.exception.file.FileSizeLimitExceededException
import com.demo.common.exception.file.InvalidExtensionException
import com.demo.common.utils.DateUtils
import com.demo.common.utils.Md5Utils
import com.demo.common.utils.StringUtils
import org.apache.commons.io.FilenameUtils
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException

class FileUploadUtils {
    companion object {
        /**
         * 默认大小 50M
         */
        const val DEFAULT_MAX_SIZE: Long = 50 * 1024 * 1024

        /**
         * 默认的文件名最大长度 100
         */
        const val DEFAULT_FILE_NAME_LENGTH: Int = 100

        /**
         * 默认上传的地址
         */
        //private var defaultBaseDir: String = RuoYiConfig.getProfile()
        private var defaultBaseDir: String = ""

        private var counter = 0

        fun setDefaultBaseDir(defaultBaseDir: String) {
            FileUploadUtils.defaultBaseDir = defaultBaseDir
        }

        fun getDefaultBaseDir(): String? {
            return defaultBaseDir
        }
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    @Throws(IOException::class)
    fun upload(file: MultipartFile): String {
        return try {
            upload(getDefaultBaseDir(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION)
        } catch (e: Exception) {
            throw IOException(e.message, e)
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    @Throws(IOException::class)
    fun upload(baseDir: String?, file: MultipartFile): String {
        return try {
            upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION)
        } catch (e: Exception) {
            throw IOException(e.message, e)
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir          相对应用的基目录
     * @param file             上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException       如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException                          比如读写文件出错时
     * @throws InvalidExtensionException            文件校验异常
     */
    @Throws(FileSizeLimitExceededException::class, IOException::class, FileNameLengthLimitExceededException::class, InvalidExtensionException::class)
    fun upload(baseDir: String?, file: MultipartFile, allowedExtension: Array<String>?): String {
        val fileNamelength = file.originalFilename!!.length
        if (fileNamelength > DEFAULT_FILE_NAME_LENGTH) {
            throw FileNameLengthLimitExceededException(DEFAULT_FILE_NAME_LENGTH)
        }
        assertAllowed(file, allowedExtension)
        val fileName = extractFilename(file)
        val desc = getAbsoluteFile(baseDir, fileName)
        file.transferTo(desc)
        return getPathFileName(baseDir, fileName)
    }

    /**
     * 编码文件名
     */
    fun extractFilename(file: MultipartFile): String? {
        var fileName = file.originalFilename
        val extension = getExtension(file)
        fileName = DateUtils.datePath().toString() + "/" + encodingFilename(fileName) + "." + extension
        return fileName
    }

    @Throws(IOException::class)
    private fun getAbsoluteFile(uploadDir: String?, fileName: String?): File {
        val desc = File(uploadDir + File.separator + fileName)
        if (!desc.parentFile.exists()) {
            desc.parentFile.mkdirs()
        }
        if (!desc.exists()) {
            desc.createNewFile()
        }
        return desc
    }

    @Throws(IOException::class)
    private fun getPathFileName(uploadDir: String?, fileName: String?): String {
        val dirLastIndex: Int = RuoYiConfig.getProfile().length() + 1
        val currentDir = StringUtils.substring(uploadDir, dirLastIndex)
        return Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName
    }

    /**
     * 编码文件名
     */
    private fun encodingFilename(fileName: String?): String? {
        var fileName = fileName
        fileName = fileName!!.replace("_", " ")
        fileName = Md5Utils.hash(fileName + System.nanoTime() + counter++)
        return fileName
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws InvalidExtensionException
     */
    @Throws(FileSizeLimitExceededException::class, InvalidExtensionException::class)
    fun assertAllowed(file: MultipartFile, allowedExtension: Array<String>?) {
        val size = file.size
        if (DEFAULT_MAX_SIZE != -1L && size > DEFAULT_MAX_SIZE) {
            throw FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024)
        }
        val fileName = file.originalFilename.toString()
        val extension = getExtension(file)
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION) {
                throw InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        fileName)
            } else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION) {
                throw InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        fileName)
            } else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION) {
                throw InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        fileName)
            } else {
                throw InvalidExtensionException(allowedExtension, extension, fileName)
            }
        }
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    fun isAllowedExtension(extension: String?, allowedExtension: Array<String>): Boolean {
        for (str in allowedExtension) {
            if (str.equals(extension, ignoreCase = true)) {
                return true
            }
        }
        return false
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    fun getExtension(file: MultipartFile): String {
        var extension = FilenameUtils.getExtension(file.originalFilename)
        if (StringUtils.isEmpty(extension)) {
            extension = MimeTypeUtils.getExtension(file.contentType)
        }
        return extension
    }
}