package me.amryousef.marvelcomics

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import me.amryousef.marvelcomics.di.DaggerApplicationComponent
import javax.inject.Inject

class ComicsApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
        super.onCreate()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}
