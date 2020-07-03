package com.demo.common.xss

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper

class XssHttpServletRequestWrapper(request: HttpServletRequest?) : HttpServletRequestWrapper(request) {

    override fun getParameterValues(name: String?): Array<String?>? {
        val values = super.getParameterValues(name)
        if (values != null) {
            var length = values.size
            var escapseValues = arrayOfNulls<String>(length)
            for (i in 0 until length) {
                // 防xss攻击和过滤前后空格
                //escapseValues[i] = EscapeUtil.clean(values[i]).trim()
            }
            return escapseValues
        }
        return super.getParameterValues(name)
    }
}