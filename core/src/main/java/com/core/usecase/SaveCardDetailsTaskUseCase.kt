package com.core.usecase

import com.core.data.FintechRepository
import com.core.domain.CardDetails

class SaveCardDetailsTaskUseCase(private val mFintechRepository: FintechRepository) {

    suspend fun invoke(listCardDetails: List<CardDetails>) {
        mFintechRepository.saveCardDetails(listCardDetails)
    }

    suspend fun invokeIsDataPresent(): Boolean {
        return mFintechRepository.isDataPresent()
    }
}