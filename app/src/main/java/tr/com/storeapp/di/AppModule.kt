package tr.com.storeapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import tr.com.storeapp.utils.AppHelper
import tr.com.storeapp.utils.STORE_APP_SHARED_PREF_KEY
import tr.com.storeapp.utils.helper.SharedHelper
import javax.inject.Singleton

/**
 * The Main [Module] that holds all app core classes and provides these instances
 */
@Module
class AppModule {

    /**
     * Provides Context
     */
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }


    /**
     * Provides Shared Preference instance
     */
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context) : SharedPreferences {
        return context.getSharedPreferences(STORE_APP_SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }

    /**
     * Provides [Gson] instance
     */
    @Provides
    fun provideGson() : Gson {
        return Gson()
    }

    /**
     * Provides [SharedHelper] instance
     */
    @Provides
    fun provideSharedHelper(sharedPreferences: SharedPreferences, gson: Gson): SharedHelper {
        return SharedHelper(sharedPreferences, gson)
    }


    /**
     * Provides [AppHelper] instance
     */
    @Singleton
    @Provides
    fun provideAppHelper(context: Context, sharedHelper: SharedHelper) : AppHelper {
        return AppHelper(context, sharedHelper)
    }

    /**
     * Provide [CompositeDisposable] instance
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}