package com.core.usecase

import com.core.base.FintechBaseUseCase
import com.core.data.FintechRepository
import com.core.domain.CardDetails

class GetCardDetailsUseCase(private val mFintechRepository: FintechRepository) :FintechBaseUseCase() {

    suspend operator fun invoke(response: (response: List<CardDetails>) -> Unit, apiError: (apiError: Throwable) -> Unit) {
        invokeFlowApi(flow = mFintechRepository.getCardDetailsFlow(), response = {
            response.invoke(it)
        }, apiError = {
            apiError.invoke(it)
        })
    }
}