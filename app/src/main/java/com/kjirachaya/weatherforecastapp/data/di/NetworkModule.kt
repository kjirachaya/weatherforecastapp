package com.kjirachaya.weatherforecastapp.data.di

import android.content.Context
import com.kjirachaya.weatherforecastapp.R
import com.kjirachaya.weatherforecastapp.data.remote.WeatherForecastApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Or whichever component scope you prefer
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiKey(
        @ApplicationContext context: Context,
    ): String = context.getString(R.string.openweather_api_key)

    @Provides
    @Singleton
    fun provideRetrofit(apiKey: String): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient(apiKey))
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKey: String): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        return OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val url =
                    original.url
                        .newBuilder()
                        .addQueryParameter("appid", apiKey) // Attach the API key here
                        .build()
                val request =
                    original
                        .newBuilder()
                        .url(url)
                        .build()
                chain.proceed(request)
            }.addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherForecastApiService(retrofit: Retrofit): WeatherForecastApi = retrofit.create(WeatherForecastApi::class.java)
}
