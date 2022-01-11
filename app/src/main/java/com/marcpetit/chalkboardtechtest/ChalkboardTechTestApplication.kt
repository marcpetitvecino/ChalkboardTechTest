package com.marcpetit.chalkboardtechtest

import android.app.Application
import com.marcpetit.chalkboardtechtest.di.appModule
import com.marcpetit.chalkboardtechtest.di.birthdaylist.birthdayListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

class ChalkboardTechTestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ChalkboardTechTestApplication)
            modules(
                appModule,
                birthdayListModule
            )
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}