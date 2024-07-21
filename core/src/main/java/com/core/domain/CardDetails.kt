package com.core.domain

data class CardDetails(
    val id: String,
    val cardName: String = "",
    val cardType: String = "",
    val cardBalance: String = "",
    val cardNumber: String = "",
    val cardExpiryDate: String = "",
    val backgroundImage: String = "",
    val isVisible: Boolean
)