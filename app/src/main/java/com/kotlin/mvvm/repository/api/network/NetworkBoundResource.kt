package com.kotlin.mvvm.repository.api.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.coroutineContext

/**
 * Created by Waheed on 04,November,2019
 */


/**
 * A generic class to send loading event up-stream when fetching data
 * only from network.
 *
 */
abstract class NetworkResource<ResultType, RequestType> {

    private val result = MutableLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) {
            result.value = Resource.loading()
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            if (shouldFetch()) {
                try {
                    fetchFromNetwork()
                } catch (e: Exception) {
                    setValue(Resource.error("An error happened: $e"))
                }
            } else {
                setValue(Resource.error("No Internet Connection"))
            }
        }
        return this
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    // ---

    private suspend fun fetchFromNetwork() {
        val apiResponse = createCallAsync().await()
        saveCallResults(processResponse(apiResponse))
        setValue(Resource.success(loadFromDb()))
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) result.postValue(newValue)
    }

    @WorkerThread
    protected abstract fun processResponse(response: RequestType): ResultType

    @WorkerThread
    protected abstract suspend fun saveCallResults(items: ResultType)

    @MainThread
    protected abstract fun shouldFetch(): Boolean

    @MainThread
    protected abstract suspend fun loadFromDb(): ResultType

    @MainThread
    protected abstract fun createCallAsync(): Deferred<RequestType>
}