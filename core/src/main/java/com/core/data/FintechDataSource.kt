package com.core.data

import com.core.domain.CardDetails
import kotlinx.coroutines.flow.Flow

interface FintechDataSource {

    suspend fun saveCardDetails(list: List<CardDetails>)

    suspend fun isDataPresent(): Boolean

    fun getCardDetailsFlow(): Flow<List<CardDetails>>
}