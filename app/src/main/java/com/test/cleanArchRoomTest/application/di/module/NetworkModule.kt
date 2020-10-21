package com.test.cleanArchRoomTest.application.di.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.test.cleanArchRoomTest.data.ApiInterface
import com.test.cleanArchRoomTest.data.repository.CharacterEpisodeRepositoryImpl
import com.test.cleanArchRoomTest.data.repository.CharactersRepositoryImpl
import com.test.cleanArchRoomTest.data.repository.EpisodeRepositoryImpl
import com.test.cleanArchRoomTest.data.repository.LocationRepositoryImpl
import com.test.cleanArchRoomTest.domain.repository.CharacterEpisodeRepository
import com.test.cleanArchRoomTest.domain.repository.CharactersRepository
import com.test.cleanArchRoomTest.domain.repository.EpisodeRepository
import com.test.cleanArchRoomTest.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiClient(): ApiInterface {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.interceptors().add(logging)
        builder.addInterceptor(logging)
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()
            .create(ApiInterface::class.java)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .build()

    }
    @Provides
    fun provideUserRepository(repo: CharactersRepositoryImpl): CharactersRepository = repo

    @Provides
    fun provideLocationRepository(repo: LocationRepositoryImpl): LocationRepository = repo

    @Provides
    fun provideEpisodeRepository(repo: EpisodeRepositoryImpl): EpisodeRepository = repo

    @Provides
    fun provideCharacterEpisodeRepository(repo: CharacterEpisodeRepositoryImpl): CharacterEpisodeRepository = repo

}