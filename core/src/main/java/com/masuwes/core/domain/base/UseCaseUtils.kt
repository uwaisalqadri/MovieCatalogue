package com.masuwes.core.domain.base

import com.masuwes.core.data.base.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

fun <T>execute(
    context: CoroutineContext = Dispatchers.Default,
    block: suspend () -> T
): Flow<T> {
    return flow {
        try {
            val out = block.invoke()
            emit(out)
        } catch (e: ApiException) {
            throw e.map()
        }
    }.flowOn(context)
}
