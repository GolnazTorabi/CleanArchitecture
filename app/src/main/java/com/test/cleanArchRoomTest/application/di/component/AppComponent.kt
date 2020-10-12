package com.test.cleanArchRoomTest.application.di.component

import android.app.Application
import com.test.cleanArchRoomTest.application.BaseApplication
import com.test.cleanArchRoomTest.application.di.module.ActivityModule
import com.test.cleanArchRoomTest.application.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: BaseApplication)
}