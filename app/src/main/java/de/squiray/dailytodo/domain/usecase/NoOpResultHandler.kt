package de.squiray.dailytodo.domain.usecase

abstract class NoOpResultHandler<T> : ResultHandler<T> {
    override fun onFinished() {
        // no-op
    }

    override fun onError(e: Throwable) {
        // no-op
    }

    override fun onSuccess(t: T) {
        // no-op
    }
}
