package de.squiray.dailytodo.domain.usecase

import de.squiray.dailytodo.util.thread.PostExecutionThread
import de.squiray.dailytodo.util.thread.ThreadExecutor
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.DisposableSubscriber
import java.util.concurrent.Callable
import java.util.concurrent.atomic.AtomicInteger


abstract class UseCase<T> constructor(private val postExecutionThread: PostExecutionThread,
                                      private val threadExecutor: ThreadExecutor) : Unsubscribable {

    private val executionId = AtomicInteger(System.currentTimeMillis().toInt() and 0x7fffffff)

    private var disposable: Disposable = io.reactivex.internal.disposables.EmptyDisposable.INSTANCE

    fun run(resultHandler: ResultHandler<T>) {
        val subscriber: DisposableSubscriber<T> = subscriber(resultHandler)
        this@UseCase.disposable = subscriber

        io.reactivex.Flowable.fromCallable(Callable<T> {
            val id = executionId.getAndIncrement()
            var failed = true
            try {
                timber.log.Timber.tag("UseCase").d("started %x", id)
                val result = execute()
                failed = false
                timber.log.Timber.tag("UseCase").d("finished %x", id)
                return@Callable result
            } finally {
                if (failed) {
                    timber.log.Timber.tag("DeleteVaultUseCase").d("failed %x", id)
                }
            }
        }).subscribeOn(io.reactivex.schedulers.Schedulers.from(threadExecutor))
                .onBackpressureLatest()
                .observeOn(postExecutionThread.scheduler)
                .subscribe(subscriber)
    }

    private fun subscriber(resultHandler: ResultHandler<T>): DisposableSubscriber<T> {
        return object : DisposableSubscriber<T>() {
            override fun onNext(result: T) {
                resultHandler.onSuccess(result)
            }

            override fun onComplete() {
                resultHandler.onFinished()
            }

            override fun onError(t: Throwable) {
                resultHandler.onError(t)
                resultHandler.onFinished()
            }
        }
    }

    override fun unsubscribe() {
        disposable.dispose()
    }

    protected abstract fun execute(): T

}
