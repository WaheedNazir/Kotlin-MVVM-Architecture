package com.kotlin.mvvm.repository.api.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.coroutineContext

/**
 * Created by Waheed on 04,November,2019
 */

/**
 * A generic class that can provide a resource backed by both the SQLite database and the network.
 * Guide](https://developer.android.com/arch).
 */
abstract class NetworkAndDBBoundResource<ResultType, RequestType> {
    /**
     * The final result LiveData
     */
    private val result = MediatorLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkAndDBBoundResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) {
            // Send loading state to UI
            result.value = Resource.loading()
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            val dbResult = loadFromDb()
            if (shouldFetch(dbResult)) {
                try {
                    fetchFromNetwork(dbResult)
                } catch (e: Exception) {
                    result.value = Resource.error("An error happened: $e")
                }
            } else {
                //Return data from local database
                setValue(Resource.success(dbResult))
            }
        }
        return this
    }

    private suspend fun fetchFromNetwork(dbResult: ResultType) {
        //Fetch data from network
        setValue(Resource.success(dbResult)) // Dispatch latest value quickly (UX purpose)
        val apiResponse = createCallAsync().await()
        //Data fetched from network
        saveCallResult(processResponse(apiResponse))
        setValue(Resource.success(loadFromDb()))
    }


    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) result.value = newValue
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected abstract fun processResponse(response: RequestType): ResultType

    @WorkerThread
    protected abstract suspend fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType): Boolean

    @MainThread
    protected abstract suspend fun loadFromDb(): ResultType

    @MainThread
    protected abstract fun createCallAsync(): Deferred<RequestType>
}