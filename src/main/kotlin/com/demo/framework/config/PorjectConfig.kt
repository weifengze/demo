package com.demo.framework.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "project", ignoreUnknownFields = true)
class PorjectConfig {
    /** 项目名称  */
    private var name: String? = null

    /** 版本  */
    private var version: String? = null

    /** 版权年份  */
    private var copyrightYear: String? = null

    /** 实例演示开关  */
    private var demoEnabled = false

    /** 上传路径  */
    private var profile: String? = null

    /** 获取地址开关  */
    private var addressEnabled = false

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getVersion(): String? {
        return version
    }

    fun setVersion(version: String?) {
        this.version = version
    }

    fun getCopyrightYear(): String? {
        return copyrightYear
    }

    fun setCopyrightYear(copyrightYear: String?) {
        this.copyrightYear = copyrightYear
    }

    fun isDemoEnabled(): Boolean {
        return demoEnabled
    }

    fun setDemoEnabled(demoEnabled: Boolean) {
        this.demoEnabled = demoEnabled
    }

    fun getProfile(): String? {
        return profile
    }

    fun setProfile(profile: String?) {
        this.profile = profile
    }

    fun isAddressEnabled(): Boolean {
        return addressEnabled
    }

    fun setAddressEnabled(addressEnabled: Boolean) {
        this.addressEnabled = addressEnabled
    }

    /**
     * 获取头像上传路径
     */
    fun getAvatarPath(): String? {
        return getProfile() + "/avatar"
    }

    /**
     * 获取下载路径
     */
    fun getDownloadPath(): String? {
        return getProfile() + "/download/"
    }

    /**
     * 获取上传路径
     */
    fun getUploadPath(): String? {
        return getProfile() + "/upload"
    }
}