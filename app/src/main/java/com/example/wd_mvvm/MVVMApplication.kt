package com.example.wd_mvvm

import android.app.Application
import com.example.wd_mvvm.injection.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MVVMApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MVVMApplication)
            modules(AppModules.modules)
        }
    }
}