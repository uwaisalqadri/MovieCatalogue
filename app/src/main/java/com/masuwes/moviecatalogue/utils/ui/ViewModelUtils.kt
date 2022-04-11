package com.masuwes.moviecatalogue.utils.ui

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import java.net.UnknownHostException

suspend fun <U> collectFlow(outputLiveData: MutableLiveData<Result<U>>, block: suspend () -> Flow<U>) {
    block.invoke().catch { cause: Throwable ->
        outputLiveData.value = Result.fail("something's wrong")
    }.collect {
        outputLiveData.value = Result.success(it)
    }
}

fun <U> LiveData<Result<U>>.observeLiveData(
    owner: LifecycleOwner,
    context: Context,
    onLoading: (() -> (Unit))? = null,
    onSuccess: (U) -> Unit,
    //onFailure: (ApiError) -> Unit
) {
    this.observe(owner) {
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
