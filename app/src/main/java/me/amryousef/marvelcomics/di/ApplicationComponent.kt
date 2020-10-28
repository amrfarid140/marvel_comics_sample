package me.amryousef.marvelcomics.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.amryousef.marvelcomics.ComicsApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        DataModuleProvider::class,
        DataModuleBinding::class,
        PresentationModule::class,
        UiModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<ComicsApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ComicsApplication): Builder
        fun build(): ApplicationComponent
    }
}
