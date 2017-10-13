package de.squiray.dailytodo.domain.usecase

/**
 * A handler for use case results.
 *
 * @param <T> The type of result this handler can handle.
</T> */
interface ResultHandler<T> {

    /**
     * Invoked after successful execution of a use case.
     *
     * @param result the use case result
     */
    fun onSuccess(result: T)

    /**
     * Invoked after failed execution of a use case.
     *
     * @param e the error that occured
     */
    fun onError(e: Throwable)

    /**
     *
     *
     * Invoked after successful and failed execution of a use case.
     *
     *
     * This method is always invoked after onSuccess / onError.
     */
    fun onFinished()

}
