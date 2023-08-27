package com.starlingbank.savings.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.starlingbank.savings.managers.TransactionsManager
import com.starlingbank.savings.repo.AccountsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.math.ceil

/**
 * Created by Prakash Nandi on 25/08/2023.
 */
@HiltViewModel
class RoundUpViewModel @Inject constructor(
    private val accountsRepo: AccountsRepo,
    private val transactionsManager: TransactionsManager
) : ViewModel() {

    var savingsAmount: MutableLiveData<String>? = MutableLiveData()

    fun getAccounts() {

        val myHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e(RoundUpViewModel::class.java.toString(), "exception: ${throwable.message}")
        }

        CoroutineScope(Dispatchers.IO).launch(myHandler) {
            val response = accountsRepo.getAccounts()

            if (response.isSuccessful) {
                val account = response.body().let { transactionResponse ->
                    transactionResponse?.accounts?.get(0)
                }

                // Instead of hard coding the dates, chose to pick up the last week's transaction
                // Get last seven day's transactions
                val transactionsResponse =
                    account?.let {
                        transactionsManager.getLastSevenDaysTransactions(
                            it.accountUid,
                            it.defaultCategory
                        )
                    }

                // Round up the transaction amount to add money towards saving goal
                if (transactionsResponse?.isSuccessful == true) {
                    var roundUpValue = 0.0
                    transactionsResponse.body()?.feedItems?.forEach { feedItem ->
                        // converting from pence to GBP
                        val transactionValue = feedItem.amount.minorUnits.toDouble() / 100

                        // round up current transaction value and add to roundUpValue variable
                        roundUpValue += ceil(transactionValue) - transactionValue
                    }

                    withContext(Dispatchers.Main) {
                        val df = DecimalFormat("#.##")
                        // Ideally I would use the currency symbol based on currency string
                        savingsAmount?.value = "${account.currency} ${df.format(roundUpValue)}"
                    }
                } else {
                    // Handle error
                }
            } else {
                // Handle error
            }
        }
    }
}