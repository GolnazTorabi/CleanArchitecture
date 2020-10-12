package com.test.cleanArchRoomTest.application.di.module

import com.test.cleanArchRoomTest.application.peresentation.dashboard.DashboardFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment
}