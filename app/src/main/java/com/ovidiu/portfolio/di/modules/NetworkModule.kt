package com.ovidiu.portfolio.di.modules

import com.ovidiu.portfolio.BuildConfig
import com.ovidiu.portfolio.architecture.model.data_source.remote.api_rest.ProfessionalApiRest
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    @JvmStatic
    @Singleton
    @Provides
    fun provideProfessionalApiRest(retrofit: Retrofit): ProfessionalApiRest {
        return retrofit.create(ProfessionalApiRest::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.google.com/")
            .client(okHttpClient)
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideOkHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder().followRedirects(false)

        interceptors.forEach {
            clientBuilder.addInterceptor(it)
        }

        return clientBuilder.build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        interceptors.add(loggingInterceptor)

        return interceptors
    }
}