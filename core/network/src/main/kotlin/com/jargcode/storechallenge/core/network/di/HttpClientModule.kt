package com.jargcode.storechallenge.core.network.di

import android.util.Log
import com.jargcode.storechallenge.core.network.BuildConfig
import com.jargcode.storechallenge.core.network.products.ProductsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object HttpClientModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        // Simplified logger for demo purposes
        val builder = OkHttpClient().newBuilder()
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("::Network response::", message)
        }.apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        builder.addNetworkInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        networkJson: Json,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideProductsApiService(
        retrofit: Retrofit,
    ): ProductsApiService = retrofit.create(ProductsApiService::class.java)


}