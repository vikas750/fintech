package com.core.data

import com.core.domain.CardDetails
import kotlinx.coroutines.flow.Flow

class FintechRepository(private val mFintechDataSource: FintechDataSource) {

    suspend fun saveCardDetails(items: List<CardDetails>) = mFintechDataSource.saveCardDetails(items)

    suspend fun isDataPresent(): Boolean = mFintechDataSource.isDataPresent()

    fun getCardDetailsFlow(): Flow<List<CardDetails>> = mFintechDataSource.getCardDetailsFlow()
}