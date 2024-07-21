package com.fintech.demo.framework.data

import com.core.data.FintechDataSource
import com.core.domain.CardDetails
import com.fintech.demo.framework.db.dao.FintechDao
import com.fintech.demo.framework.db.entity.CardDetailsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FintechDataSourceImpl(
    private val mFintechDao: FintechDao,
) : FintechDataSource {

    override suspend fun saveCardDetails(list: List<CardDetails>) = withContext(Dispatchers.IO) {
        mFintechDao.insertAll(list.toCardDetailsEntity())
    }

    override suspend fun isDataPresent(): Boolean = withContext(Dispatchers.IO) {
        mFintechDao.getCount() > 0
    }

    override fun getCardDetailsFlow(): Flow<List<CardDetails>> =
        mFintechDao.getCardDetailsAsFlow().flowOn(Dispatchers.IO).distinctUntilChanged()
            .map { entities -> entities.map { it.toCardDetails() } }


    private fun List<CardDetails>.toCardDetailsEntity(): List<CardDetailsEntity> {
        return this.map { cardDetails ->
            CardDetailsEntity(
                id = cardDetails.id,
                cardName = cardDetails.cardName,
                cardType = cardDetails.cardType,
                cardBalance = cardDetails.cardBalance,
                cardNumber = cardDetails.cardNumber,
                cardExpiryDate = cardDetails.cardExpiryDate,
                backgroundImage = cardDetails.backgroundImage,
                isVisible = cardDetails.isVisible
            )
        }
    }

    private fun CardDetailsEntity.toCardDetails(): CardDetails = CardDetails(
        id = id,
        cardName = cardName,
        cardType = cardType,
        cardBalance = cardBalance,
        cardNumber = cardNumber,
        cardExpiryDate = cardExpiryDate,
        backgroundImage = backgroundImage,
        isVisible = isVisible
    )

}