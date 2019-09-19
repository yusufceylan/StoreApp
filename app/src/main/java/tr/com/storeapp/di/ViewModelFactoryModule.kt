package tr.com.storeapp.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import tr.com.storeapp.vm.ViewModelFactory
import javax.inject.Singleton

@Module
abstract class ViewModelFactoryModule {

    /**
     * Returns an instance of [ViewModelFactory]
     */
    @Singleton
    @Binds
    abstract fun bindViewModelFactoryModule(viewModelFactory : ViewModelFactory) : ViewModelProvider.Factory

}