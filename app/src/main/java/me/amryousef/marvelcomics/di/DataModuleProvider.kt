package me.amryousef.marvelcomics.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import me.amryousef.comics.data.api.ComicsService
import me.amryousef.lib.data.ApiKeyInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class DataModuleProvider {
    @Provides
    @Singleton
    fun provideMoshi() = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        moshi: Moshi,
        apiKeyInterceptor: ApiKeyInterceptor
    ): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
        return Retrofit
            .Builder()
            .client(client)
            .baseUrl("https://gateway.marvel.com:443/v1/public/")
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            ).build()
    }

    @Provides
    @Singleton
    fun provideComicsService(retrofit: Retrofit): ComicsService {
        return retrofit.create(ComicsService::class.java)
    }
}
