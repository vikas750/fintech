package com.fintech.demo.presentation.ui.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.CardDetails
import com.core.domain.Contacts
import com.core.usecase.GetCardDetailsUseCase
import com.core.usecase.SaveCardDetailsTaskUseCase
import kotlinx.coroutines.launch
import java.util.UUID

class HomeScreenViewModel(
    private val saveCardDetails: SaveCardDetailsTaskUseCase,
    private val getCardDetails: GetCardDetailsUseCase,
) : ViewModel() {

    private val getCardDetailsMutableLiveData = MutableLiveData<List<CardDetails>>()
    val getCardDetailsLiveData: LiveData<List<CardDetails>> get() = getCardDetailsMutableLiveData

    private val getContactMutableLiveData = MutableLiveData<List<Contacts>>()
    val getContactLiveData: LiveData<List<Contacts>> get() = getContactMutableLiveData

    fun saveCardDetails(listCardDetails: List<CardDetails>, onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            saveCardDetails.invoke(listCardDetails)
            onSuccess.invoke("Success")
        }
    }

    fun isDataPresent(onSuccess: (Boolean) -> Unit) {
        viewModelScope.launch {
            onSuccess.invoke(saveCardDetails.invokeIsDataPresent())
        }
    }

    fun getCardDetails() {
        viewModelScope.launch {
            getCardDetails.invoke(response = {
                onSuccessCardDetails(it)
            }, apiError = {
                onErrorCardDetails(it)
            })
        }

    }

    private fun onSuccessCardDetails(response: List<CardDetails>) {
        getCardDetailsMutableLiveData.value = response
    }

    private fun onErrorCardDetails(error: Throwable) {

    }

    fun getContactList() {
        val cardDetailsList = listOf(
            Contacts(
                id = UUID.randomUUID().toString(),
                name = "Vikas Singh",
                profileImage = "",
                time = "16:15",
                transactionType = "Transfer",
                transactionAmount = "-$120.50"
            ), Contacts(
                id = UUID.randomUUID().toString(),
                name = "Vikas Singh",
                profileImage = "",
                time = "16:15",
                transactionType = "Transfer",
                transactionAmount = "-$120.50"
            ), Contacts(
                id = UUID.randomUUID().toString(),
                name = "Vikas Singh",
                profileImage = "",
                time = "16:15",
                transactionType = "Transfer",
                transactionAmount = "-$120.50"
            ), Contacts(
                id = UUID.randomUUID().toString(),
                name = "Vikas Singh",
                profileImage = "",
                time = "16:15",
                transactionType = "Transfer",
                transactionAmount = "-$120.50"
            ), Contacts(
                id = UUID.randomUUID().toString(),
                name = "Vikas Singh",
                profileImage = "",
                time = "16:15",
                transactionType = "Transfer",
                transactionAmount = "-$120.50"
            ), Contacts(
                id = UUID.randomUUID().toString(),
                name = "Vikas Singh",
                profileImage = "",
                time = "16:15",
                transactionType = "Transfer",
                transactionAmount = "-$120.50"
            ), Contacts(
                id = UUID.randomUUID().toString(),
                name = "Vikas Singh",
                profileImage = "",
                time = "16:15",
                transactionType = "Transfer",
                transactionAmount = "-$120.50"
            ), Contacts(
                id = UUID.randomUUID().toString(),
                name = "Vikas Singh",
                profileImage = "",
                time = "16:15",
                transactionType = "Transfer",
                transactionAmount = "-$120.50"
            ), Contacts(
                id = UUID.randomUUID().toString(),
                name = "Vikas Singh",
                profileImage = "",
                time = "16:15",
                transactionType = "Transfer",
                transactionAmount = "-$120.50"
            ), Contacts(
                id = UUID.randomUUID().toString(),
                name = "Vikas Singh",
                profileImage = "",
                time = "16:15",
                transactionType = "Transfer",
                transactionAmount = "-$120.50"
            ), Contacts(
                id = UUID.randomUUID().toString(),
                name = "Vikas Singh",
                profileImage = "",
                time = "16:15",
                transactionType = "Transfer",
                transactionAmount = "-$120.50"
            ), Contacts(
                id = UUID.randomUUID().toString(),
                name = "Vikas Singh",
                profileImage = "",
                time = "16:15",
                transactionType = "Transfer",
                transactionAmount = "-$120.50"
            )
        )
        getContactMutableLiveData.value = cardDetailsList
    }

    fun getCardDetails(onSuccess: (Boolean) -> Unit) {
        viewModelScope.launch {
            onSuccess.invoke(saveCardDetails.invokeIsDataPresent())
        }
    }
}