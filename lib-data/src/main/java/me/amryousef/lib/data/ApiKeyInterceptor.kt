package me.amryousef.lib.data

import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import java.util.Date
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = Date().time.toString()
        val url = chain
            .request()
            .url()
            .newBuilder()
            .addQueryParameter("apikey", API_KEY)
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("hash", generateHash(timestamp))
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }

    @Suppress("MagicNumber")
    private fun generateHash(timestamp: String): String {
        val hexChars = "0123456789abcdef"
        val bytes = MessageDigest
            .getInstance("MD5")
            .digest("$timestamp$PRIVATE_KEY$API_KEY".toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(hexChars[i shr 4 and 0x0f])
            result.append(hexChars[i and 0x0f])
        }

        return result.toString()
    }

    private companion object {
        const val API_KEY = "f8370e1f339c2171cc909402d300ea44"
        const val PRIVATE_KEY = "f3ec46ac173c8f94d67c261e58f53f90b9b750bc"
    }
}
