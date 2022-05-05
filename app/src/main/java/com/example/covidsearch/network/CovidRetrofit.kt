package com.example.rxjavaretrofit.network


import com.example.rxjavaretrofit.CovidService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CovidRetrofit {

    private val retrofit = Retrofit.Builder()
        .baseUrl(CovidApi.DOMAIN)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(covidOkHttpClient(covidLoggingInterceptor()))
        .build()

    private fun covidOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient{
        val b = OkHttpClient.Builder()
        //이 클라이언트를 통해 오고 가는 네트워크 요청/응답을 로그로 표시하도록 함
        return b.build()
    }

    private fun covidLoggingInterceptor(): HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor

    }

    val covidApiService: CovidService by lazy {
        retrofit.create(CovidService::class.java)
    }
}