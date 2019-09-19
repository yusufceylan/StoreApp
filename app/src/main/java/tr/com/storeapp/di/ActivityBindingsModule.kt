package tr.com.storeapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tr.com.storeapp.di.module.MainActivityModule
import tr.com.storeapp.di.module.ProductListActivityModule
import tr.com.storeapp.di.scope.ActivityScope
import tr.com.storeapp.ui.MainActivity
import tr.com.storeapp.ui.product.ProductListActivity

/**
 * [Module] that provide injector all activity classes
 */
@Module
abstract class ActivityBindingsModule {
    /**
     * Helps inject at [MainActivity]t
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun mainActivityInjector() : MainActivity

    /**
     * Helps inject at [ProductListActivity]
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = [ProductListActivityModule::class])
    internal abstract fun productListActivityInjector() : ProductListActivity

}