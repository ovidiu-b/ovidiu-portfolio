package com.ovidiu.portfolio.di.modules

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NetworkModule {
    @JvmStatic
    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    /*@JvmStatic
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

       *//* interceptors.forEach {
            clientBuilder.addInterceptor(it)
        }*//*

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
    }*/
}