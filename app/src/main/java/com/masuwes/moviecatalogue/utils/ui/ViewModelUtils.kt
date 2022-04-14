package com.masuwes.moviecatalogue.utils.ui

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.masuwes.core.domain.base.ApiError
import kotlinx.coroutines.flow.*
import java.net.UnknownHostException

suspend fun <U> collectFlow(outputLiveData: MutableLiveData<Result<U>>, block: suspend () -> Flow<U>) {
    block.invoke().catch { cause: Throwable ->
        outputLiveData.value = Result.fail("something's wrong")
    }.collect {
        outputLiveData.value = Result.success(it)
    }
}

suspend fun <U> collectFlow(outputLiveData: MutableSharedFlow<Result<U>>, block: suspend () -> Flow<U>) {
    block.invoke().catch { cause: Throwable ->
        outputLiveData.emit(Result.fail("something's wrong"))
    }.collect {
        outputLiveData.emit(Result.success(it))
    }
}

fun <U> LiveData<Result<U>>.observeData(
    owner: LifecycleOwner,
    context: Context,
    onLoading: (() -> (Unit))? = null,
    onSuccess: (U) -> Unit,
    onFailure: (ApiError) -> Unit
) {
    var apiError: ApiError

    this.observe(owner) {
        when (it) {
            is Result.Loading -> {
                onLoading?.invoke()
            }

            is Result.Success -> {
                onSuccess.invoke(it.data)
            }

            is Result.Failure -> {
                if (it.throwable is ApiError) {
                    apiError = it.throwable
                    onFailure.invoke(apiError)
                }
            }

            else -> {}
        }
    }
}

fun <U> SharedFlow<Result<U>>.observeData(
    owner: LifecycleOwner,
    context: Context,
    onLoading: (() -> (Unit))? = null,
    onSuccess: (U) -> Unit,
    //onFailure: (ApiError) -> Unit
) {
    owner.lifecycleScope.launchWhenStarted {
        this@observeData.collect {
            when (it) {
                is Result.Loading -> {
                    onLoading?.invoke()
                }

                is Result.Success -> {
                    onSuccess.invoke(it.data)
                }

                is Result.Failure -> {
                }

                else -> {}
            }
        }
    }
}
