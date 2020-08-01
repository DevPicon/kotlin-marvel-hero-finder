package pe.devpicon.android.marvelheroes.data.remote

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import pe.androidperu.marvelheroes.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest

object RestClient {
    private val httpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(
                object : Interceptor {
                    override fun intercept(chain: Chain): Response {
                        val originalRequest = chain.request()
                        val originalHttpUrl = originalRequest.url

                        // https://developer.marvel.com/documentation/authorization
                        val timeStamp = System.currentTimeMillis()
                        val input = "${timeStamp}${BuildConfig.MARVEL_PRIVATE_KEY}${BuildConfig.MARVEL_PUBLIC_KEY}"
                        val hash = MessageDigest.getInstance("MD5")
                                .digest(input.toByteArray()).joinToString("") {
                                    // https://www.javacodemonk.com/md5-and-sha256-in-java-kotlin-and-android-96ed9628
                                    "%02x".format(it)
                                }

                        val url = originalHttpUrl.newBuilder()
                                .addQueryParameter("ts", "$timeStamp")
                                .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_KEY)
                                .addQueryParameter("hash", hash)
                                .build()

                        val requestBuilder = originalRequest.newBuilder()
                                .url(url)

                        val request = requestBuilder.build()
                        return chain.proceed(request)
                    }
                }
        )

        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }

    private val retrofit = Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl("http://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val service = retrofit.create(MarvelService::class.java)
}