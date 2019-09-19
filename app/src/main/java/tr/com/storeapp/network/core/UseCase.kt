package tr.com.storeapp.network.core

import androidx.annotation.Nullable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Base UseCase class
 * [M] -> Response Model Object
 * [P] -> Request Parameters
 */
abstract class UseCase<M, P> {

    abstract fun buildUseCaseObservable(@Nullable params: P?): Single<M>

    fun execute(observer: DisposableSingleObserver<M>, @Nullable params: P?): Disposable {

        val observable: Single<M> = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.newThread())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        return observable.subscribeWith(observer)
    }
}