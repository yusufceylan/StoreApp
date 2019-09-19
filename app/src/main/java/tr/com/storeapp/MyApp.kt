package tr.com.storeapp

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import tr.com.storeapp.di.DaggerAppComponent

/**
 * Base Application class that handles core process
 */
class MyApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    /**
     * Returns the injector
     */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        val daggerAppComponent = DaggerAppComponent.factory().create(this)
        daggerAppComponent.inject(this)
        return daggerAppComponent

    }

    /**
     * Initialize Timber for logging
     */
    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}