package com.example.wd_mvvm

import android.app.Application
import com.example.wd_mvvm.injection.AppModules
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MVVMApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
        startKoin {
            androidContext(this@MVVMApplication)
            modules(AppModules.modules)
        }
    }
}