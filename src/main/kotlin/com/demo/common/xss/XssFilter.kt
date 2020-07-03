package com.demo.common.xss

import org.apache.commons.lang3.StringUtils
import java.util.regex.Pattern
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 防止XSS攻击的过滤器
 */
class XssFilter : Filter {
    /**
     * 排除链接
     */
    var excludes = ArrayList<String>()

    /**
     * xss过滤开关
     */
    var enabled = false

    /**
     *
     */
    override fun init(filterConfig: FilterConfig?) { // ?->表示可为空
        // !!->抛出空指针异常
        var tempExcludes = filterConfig!!.getInitParameter("excludes")
        var tempEnabled = filterConfig.getInitParameter("enabled")
        if (StringUtils.isNotEmpty(tempExcludes)) {
            var url = tempExcludes.split(",".toRegex()).toTypedArray()
            var i = 0
            while (url != null && i < url.size) {
                excludes.add(url[i])
                i++
            }
        }
        if (StringUtils.isNotEmpty(tempEnabled)) {
            enabled = Boolean.equals(tempEnabled)
        }
    }


    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        var req = request as HttpServletRequest
        var resp = response as HttpServletResponse
        if (handleExcludeURL(req, resp)) {
            chain!!.doFilter(request, response)
            return
        }
        var xssRequest = XssHttpServletRequestWrapper(request as HttpServletRequest?)
        chain!!.doFilter(xssRequest, response)
    }

    private fun handleExcludeURL(request: HttpServletRequest, response: HttpServletResponse): Boolean {
        if (!enabled) {
            return true
        }
        if (excludes == null || excludes.isEmpty()) {
            return false
        }
        var url = request.servletPath
        for (pattern in excludes) {
            var p = Pattern.compile("^$pattern")
            var m = p.matcher(url)
            if (m.find()) {
                return true
            }
        }
        return false
    }

    override fun destroy() {}
}