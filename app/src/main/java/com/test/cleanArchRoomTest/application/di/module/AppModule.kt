package com.test.cleanArchRoomTest.application.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.test.cleanArchRoomTest.application.di.component.ViewModelSubComponent
import com.test.cleanArchRoomTest.application.di.factory.ViewModelFactory
import com.test.cleanArchRoomTest.data.ApiInterface
import com.test.cleanArchRoomTest.data.database.AppDatabase
import com.test.cleanArchRoomTest.data.repository.CharactersRepositoryImpl
import com.test.cleanArchRoomTest.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module(subcomponents = [ViewModelSubComponent::class])
internal class AppModule {
    @Inject
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
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }


    @Provides
    fun provideViewModelFactory(viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory {
        return ViewModelFactory(viewModelSubComponent.build())
    }

    /*  @Singleton
    @Inject
    @Provides
    fun provideLoggingInterceptor(appSharedPreferences: AppSharedPreferences): LoggingInterceptor.Builder {
        return LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .addHeader("Authorization", appSharedPreferences.getAuthToken())
            .addHeader("Accept", "application/json")
            .setLevel(Level.BASIC)
            .request("Request")
            .response("Response")


    }
*/

      @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "AppData")
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserRepository(repo: CharactersRepositoryImpl): CharactersRepository = repo

    /*@Provides
    fun provideSharedPreferences(repo: AppSharedPreferences): SharedPreferencesHelper = repo
*/
/*
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }*/


}


