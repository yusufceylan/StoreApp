package tr.com.storeapp.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tr.com.storeapp.di.qualifier.ViewModelKey
import tr.com.storeapp.ui.product.ProductListActivityViewModel

@Module
abstract class ProductListActivityModule {

    /**
     * Binds [ProductListActivityViewModel]
     */
    @Binds
    @IntoMap
    @ViewModelKey(ProductListActivityViewModel::class)
    internal abstract fun provideProductListActivityViewModel(productListActivityViewModel: ProductListActivityViewModel) : ViewModel

}