package com.kotlin.mvvm.utils

import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException


object ApiErrorHandling {

    private const val ERROR_SOMETHING_WENT_WRONG = "Something went wrong."
    private const val ERROR_REQUEST_CANCELLED = "Request cancelled."
    private const val ERROR_REQUEST_TIMED_OUT = "Request timed out."
    private const val ERROR_CONNECTION_TIMED_OUT = "Connection timed out."

    /**
     * Return error on the basis of exception and api call status
     */
    fun error(throwable: Throwable, isCancelled: Boolean): String {
        try {
            throwable.let {
                return when {
                    it is HttpException -> return (it.response()?.errorBody()?.string())
                        ?: ERROR_SOMETHING_WENT_WRONG

                    it is SocketTimeoutException -> ERROR_CONNECTION_TIMED_OUT

                    it is IOException -> ERROR_REQUEST_TIMED_OUT

                    isCancelled -> ERROR_REQUEST_CANCELLED

                    else -> ERROR_SOMETHING_WENT_WRONG
                }
            }
        } catch (e: Exception) {
            return throwable.message ?: ERROR_SOMETHING_WENT_WRONG
        }
    }
}