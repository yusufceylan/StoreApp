package tr.com.storeapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import tr.com.storeapp.MyApp
import javax.inject.Singleton

/**
 * The main component that holds and services all modules within its builder.
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ViewModelFactoryModule::class, AppModule::class,
    ApiModule::class, ActivityBindingsModule::class])
interface AppComponent : AndroidInjector<MyApp> {

    /**
     * A {@see [Component.Factory]} that initializes necessary implementations
     */
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

}