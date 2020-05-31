package br.com.nybooks.data

import br.com.nybooks.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiService {
    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.NYT_BOOKS_API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(initClient())
            .build()
    }

    private fun initClient() : OkHttpClient {

        val apiKeyInterceptor = Interceptor { chain ->
            var request = chain.request()
            val url: HttpUrl = request.url()
                .newBuilder()
                .addQueryParameter("api-key", BuildConfig.NYT_BOOKS_API_KEY)
                .build()

            request = request.newBuilder().url(url).build()

            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addNetworkInterceptor(apiKeyInterceptor)
            .build();
    }

    val service: NYTServices = initRetrofit().create(NYTServices::class.java)
}