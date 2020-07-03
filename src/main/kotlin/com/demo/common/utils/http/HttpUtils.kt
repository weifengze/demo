package com.demo.common.utils.http

import com.demo.common.constant.Constants
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.URL
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

class HttpUtils {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(HttpUtils::class.java)
        fun sendGet(url: String, param: String): String {
            return sendGet(url, param, Constants.UTF8)
        }

        /**
         * 向指定 URL 发送GET方法的请求
         *
         * @param url         发送请求的 URL
         * @param param       请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
         * @param contentType 编码类型
         * @return 所代表远程资源的响应结果
         */
        fun sendGet(url: String, param: String, contentType: String): String {
            val result = StringBuilder()
            var bufferedReader: BufferedReader? = null
            try {
                val urlNameString = "$url?$param"
                LOGGER.info("sendGet - {}", urlNameString)
                val realUrl = URL(urlNameString)
                val connection = realUrl.openConnection()
                connection.setRequestProperty("accept", "*/*")
                connection.setRequestProperty("connection", "Keep-Alive")
                connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
                connection.connect()
                bufferedReader = BufferedReader(InputStreamReader(connection.getInputStream(), contentType))
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    result.append(line)
                }
                LOGGER.info("recv - {}", result)
            } catch (e: ConnectException) {
                LOGGER.error("调用HttpUtils.sendGet ConnectException, url=$url,param=$param", e)
            } catch (e: SocketTimeoutException) {
                LOGGER.error("调用HttpUtils.sendGet SocketTimeoutException, url=$url,param=$param", e)
            } catch (e: IOException) {
                LOGGER.error("调用HttpUtils.sendGet IOException, url=$url,param=$param", e)
            } catch (e: Exception) {
                LOGGER.error("调用HttpsUtil.sendGet Exception, url=$url,param=$param", e)
            } finally {
                try {
                    // 因为Kotlin空安全,此抛空异常
                    bufferedReader!!.close()
                } catch (ex: Exception) {
                    LOGGER.error("调用in.close Exception, url=$url,param=$param", ex)
                }
            }
            return result.toString()
        }

        /**
         * 向指定 URL 发送POST方法的请求
         *
         * @param url   发送请求的 URL
         * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
         * @return 所代表远程资源的响应结果
         */
        fun sendPost(url: String, param: String): String? {
            var out: PrintWriter? = null
            var bufferedReader: BufferedReader? = null
            val result = StringBuilder()
            try {
                val urlNameString = "$url?$param"
                LOGGER.info("sendPost - {}", urlNameString)
                val realUrl = URL(urlNameString)
                val conn = realUrl.openConnection()
                conn.setRequestProperty("accept", "*/*")
                conn.setRequestProperty("connection", "Keep-Alive")
                conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
                conn.setRequestProperty("Accept-Charset", "utf-8")
                conn.setRequestProperty("contentType", "utf-8")
                conn.doOutput = true
                conn.doInput = true
                out = PrintWriter(conn.getOutputStream())
                out.print(param)
                out.flush()
                bufferedReader = BufferedReader(InputStreamReader(conn.getInputStream(), "utf-8"))
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    result.append(line)
                }
                LOGGER.info("recv - {}", result)
            } catch (e: ConnectException) {
                LOGGER.error("调用HttpUtils.sendPost ConnectException, url=$url,param=$param", e)
            } catch (e: SocketTimeoutException) {
                LOGGER.error("调用HttpUtils.sendPost SocketTimeoutException, url=$url,param=$param", e)
            } catch (e: IOException) {
                LOGGER.error("调用HttpUtils.sendPost IOException, url=$url,param=$param", e)
            } catch (e: Exception) {
                LOGGER.error("调用HttpsUtil.sendPost Exception, url=$url,param=$param", e)
            } finally {
                try {
                    out?.close()
                    bufferedReader?.close()
                } catch (ex: IOException) {
                    LOGGER.error("调用in.close Exception, url=$url,param=$param", ex)
                }
            }
            return result.toString()
        }

        fun sendSSLPost(url: String, param: String): String? {
            val result = StringBuilder()
            val urlNameString = "$url?$param"
            try {
                LOGGER.info("sendSSLPost - {}", urlNameString)
                val sc = SSLContext.getInstance("SSL")
                sc.init(null, arrayOf<TrustManager>(TrustAnyTrustManager()), SecureRandom())
                val console = URL(urlNameString)
                val conn = console.openConnection() as HttpsURLConnection
                conn.setRequestProperty("accept", "*/*")
                conn.setRequestProperty("connection", "Keep-Alive")
                conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
                conn.setRequestProperty("Accept-Charset", "utf-8")
                conn.setRequestProperty("contentType", "utf-8")
                conn.doOutput = true
                conn.doInput = true
                conn.sslSocketFactory = sc.socketFactory
                conn.hostnameVerifier = TrustAnyHostnameVerifier()
                conn.connect()
                val inputStream = conn.inputStream
                val br = BufferedReader(InputStreamReader(inputStream))
                var ret: String? = ""
                while (br.readLine().also { ret = it } != null) {
                    if (ret != null && ret!!.trim { it <= ' ' } != "") {
                        result.append(String(ret!!.toByteArray(charset("ISO-8859-1")), Charsets.UTF_8))
                    }
                }
                LOGGER.info("recv - {}", result)
                conn.disconnect()
                br.close()
            } catch (e: ConnectException) {
                LOGGER.error("调用HttpUtils.sendSSLPost ConnectException, url=$url,param=$param", e)
            } catch (e: SocketTimeoutException) {
                LOGGER.error("调用HttpUtils.sendSSLPost SocketTimeoutException, url=$url,param=$param", e)
            } catch (e: IOException) {
                LOGGER.error("调用HttpUtils.sendSSLPost IOException, url=$url,param=$param", e)
            } catch (e: Exception) {
                LOGGER.error("调用HttpsUtil.sendSSLPost Exception, url=$url,param=$param", e)
            }
            return result.toString()
        }

        private class TrustAnyTrustManager : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }

        private class TrustAnyHostnameVerifier : HostnameVerifier {
            override fun verify(hostname: String, session: SSLSession): Boolean {
                return true
            }
        }
    }
}