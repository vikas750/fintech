package com.core.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

open class FintechBaseUseCase {
    open suspend fun <T : Any> invokeFlowApi(flow: Flow<T>, showLoader: Boolean = true, response: (response: T) -> Unit, apiError: (apiError: Throwable) -> Unit) {
        return flow.onStart {
            //Show loader
        }.catch {
            //Error handler
        }.collect {
            //Hide loader
            response.invoke(it)
        }
    }
}