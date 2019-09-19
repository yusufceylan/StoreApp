package tr.com.storeapp.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tr.com.storeapp.di.qualifier.ViewModelKey
import tr.com.storeapp.ui.MainViewModel

@Module
abstract class MainActivityModule {

    /**
     * Binds [MainViewModel]
     */
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun provideMainViewModel(mainViewModel: MainViewModel) : ViewModel

}