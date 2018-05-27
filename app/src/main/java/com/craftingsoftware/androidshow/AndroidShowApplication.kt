package com.craftingsoftware.androidshow

import android.app.Application
import com.craftingsoftware.androidshow.di.AppComponent
import com.craftingsoftware.androidshow.di.DaggerAppComponent
import timber.log.Timber

/**
 * Created by constantin.cheptea
 * on 22/05/2018.
 */
class AndroidShowApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    companion object {
        private var appComponent: AppComponent? = null

        fun diInjector(): AppComponent = when (appComponent) {
            null -> throw RuntimeException("Dagger is not ready. Please initialize it at the top of Application#onCreate() method.")
            else -> appComponent!!
        }
    }
}