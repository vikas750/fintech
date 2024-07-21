package com.fintech.demo.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Card_Details")
data class CardDetailsEntity(
    @ColumnInfo(name = "id") @PrimaryKey val id: String = "0",
    @ColumnInfo(name = "cardName") val cardName: String,
    @ColumnInfo(name = "cardType") val cardType: String,
    @ColumnInfo(name = "cardBalance") val cardBalance: String,
    @ColumnInfo(name = "cardNumber") val cardNumber: String,
    @ColumnInfo(name = "cardExpiryDate") val cardExpiryDate: String,
    @ColumnInfo(name = "backgroundImage") val backgroundImage: String,
    @ColumnInfo(name = "isVisible") val isVisible: Boolean,
)
