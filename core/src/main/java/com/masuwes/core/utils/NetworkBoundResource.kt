package com.masuwes.core.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import java.util.concurrent.Executor


//abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: Executor) {
//
//    private val result = MediatorLiveData<Resource<ResultType>>()
//
//    init {
//        result.value = Resource.loading(null)
//
//        @Suppress("LeakingThis")
//        val dbSource = loadFromDB()
//
//        result.addSource(dbSource) { data ->
//            result.removeSource(dbSource)
//            if (shouldFetch(data)) {
//                fetchNetwork(dbSource)
//            } else {
//                result.addSource(dbSource) { newData ->
//                    result.value =
//                        Resource.success(newData)
//                }
//            }
//        }
//    }
//
//    private fun onFetchFailed() {}
//
//    protected abstract fun loadFromDB(): LiveData<ResultType>
//
//    protected abstract fun shouldFetch(data: ResultType?): Boolean
//
//    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>?
//
//    protected abstract fun saveCallResult(data: RequestType)
//
//    private fun fetchNetwork(dbSource: LiveData<ResultType>) {
//
//        val apiResponse = createCall()
//
//        result.addSource(dbSource) { newData ->
//            result.value =
//                Resource.loading(newData)
//        }
//
//        result.addSource(apiResponse!!) { response ->
//            result.removeSource(apiResponse)
//            result.removeSource(dbSource)
//            when (response.statusResponse) {
//                StatusResponse.SUCCESS ->
//                    mExecutors.diskIO().execute {
//                        response.body?.let { saveCallResult(it) }
//                        mExecutors.mainThread().execute {
//                            result.addSource(loadFromDB()) { newData ->
//                                result.value =
//                                    Resource.success(
//                                        newData
//                                    )
//                            }
//                        }
//                    }
//
//                StatusResponse.EMPTY -> mExecutors.mainThread().execute {
//                    result.addSource(loadFromDB()) { newData ->
//                        result.value =
//                            Resource.success(
//                                newData
//                            )
//                    }
//                }
//
//                StatusResponse.ERROR -> {
//                    onFetchFailed()
//                    result.addSource(dbSource) { newData ->
//                        result.value = response.message?.let {
//                            Resource.error(
//                                it,
//                                newData
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    fun asLiveData(): LiveData<Resource<ResultType>> = result
//}