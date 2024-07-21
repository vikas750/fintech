package com.fintech.demo.presentation.ui

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.core.data.FintechRepository
import com.core.usecase.GetCardDetailsUseCase
import com.core.usecase.SaveCardDetailsTaskUseCase
import com.fintech.demo.framework.db.FintechAppDatabase
import com.fintech.demo.presentation.ui.homescreen.HomeScreenViewModel

class ViewModelFactory(
    private val mApplication: Application,
    private val mSharedPreferences: SharedPreferences,
) : ViewModelProvider.Factory {

    private val mDatabase: FintechAppDatabase by lazy { FintechAppDatabase.getInstance(mApplication) }
    private val mFintechRepository: FintechRepository by lazy {
        FintechRepository(com.fintech.demo.framework.data.FintechDataSourceImpl(mDatabase.taskHistoryDao()))
    }

    private val mSaveCardDetails: SaveCardDetailsTaskUseCase by lazy {
        SaveCardDetailsTaskUseCase(
            mFintechRepository
        )
    }
    private val mGetCardDetails: GetCardDetailsUseCase by lazy {
        GetCardDetailsUseCase(
            mFintechRepository
        )
    }



    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            // region B

            isAssignableFrom(HomeScreenViewModel::class.java) -> {
                HomeScreenViewModel(mSaveCardDetails, mGetCardDetails)
            }
            // region end
            else -> {
                throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
            }
        }
    } as T

    companion object {

        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                ViewModelFactory(
                    application, application.getSharedPreferences(
                        "com.fintech.demo", Context.MODE_PRIVATE
                    )
                ).apply { INSTANCE = this }
            }
    }
}