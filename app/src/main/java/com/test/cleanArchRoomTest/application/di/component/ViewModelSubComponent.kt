package com.test.cleanArchRoomTest.application.di.component

import com.test.cleanArchRoomTest.application.peresentation.dashboard.DashboardViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun dashboardViewModel(): DashboardViewModel
}