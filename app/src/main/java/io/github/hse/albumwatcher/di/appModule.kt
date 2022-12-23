package io.github.hse.albumwatcher.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.hse.albumwatcher.utils.Constants
import io.github.hse.albumwatcher.data.VkApiService
import io.github.hse.albumwatcher.data.VkRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()).asLenient())
            .build()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        get<Retrofit>().create(VkApiService::class.java)
    }

    single {
        VkRepository(get())
    }
}