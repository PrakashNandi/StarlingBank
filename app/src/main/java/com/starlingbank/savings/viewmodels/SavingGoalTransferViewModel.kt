package com.starlingbank.savings.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.starlingbank.savings.models.Amount
import com.starlingbank.savings.models.SavingsGoalRequest
import com.starlingbank.savings.models.TopUpRequest
import com.starlingbank.savings.repo.AccountsRepo
import com.starlingbank.savings.repo.SavingsGoalRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class SavingGoalTransferViewModel @Inject constructor(
    private val accountsRepo: AccountsRepo,
    private val savingsGoalRepo: SavingsGoalRepo
) : ViewModel() {

    var success: MutableLiveData<Boolean> = MutableLiveData()

    fun transferTowardsSavingsGoal(amount: Double) {

        val myHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e(
                SavingGoalTransferViewModel::class.java.toString(),
                "exception: ${throwable.message}"
            )
        }

        CoroutineScope(Dispatchers.IO).launch(myHandler) {
            val response = accountsRepo.getAccounts()

            if (response.isSuccessful) {
                // Get current account details
                val account = response.body().let { transactionResponse ->
                    transactionResponse?.accounts?.get(0)
                }

                 /*Ideally the savings goal ID should be selected from UI i.e. may be from a drop down list of savings goals.
                 But here I am using the first existing savings goal to add money.
                 If savings goal does not exist for the account, creating a savings goal and adding the rounded up amount to it.*/
                val savingsGoalsResponse =
                    account?.let { savingsGoalRepo.getSavingsGoals(it.accountUid) }

                var savingsGoalUid = ""
                if (savingsGoalsResponse?.isSuccessful == true && savingsGoalsResponse.body()?.savingsGoalList?.isEmpty() == false) {
                    savingsGoalUid =
                        savingsGoalsResponse.body()?.savingsGoalList?.get(0)?.savingsGoalUid.toString()
                } else {
                    // Create a new savings goal
                    val savingsGoalRequest =
                        account?.let {
                            SavingsGoalRequest(
                                "Trip to Cornwall",
                                it.currency,
                                Amount(it.currency, (100 * 100).toString()),
                                ""
                            )
                        }
                    val savingsGoalResponse =
                        account?.let {
                            savingsGoalRepo.create(
                                it.accountUid,
                                savingsGoalRequest!!
                            )
                        }

                    if (savingsGoalResponse?.isSuccessful == true) {
                        savingsGoalUid = savingsGoalResponse.body()?.savingsGoalUid.toString()
                    }
                }

                if (savingsGoalUid.isEmpty()) {
                    // Handle error
                } else {
                    val topUpRequest =
                        account?.let { Amount(it.currency, (amount * 100).toInt().toString()) }
                            ?.let { TopUpRequest(it) }

                    // Add rounded up amount towards savings goal
                    val savingsGoalTransferResponse = account?.let {
                        savingsGoalRepo.addMoney(
                            it.accountUid,
                            savingsGoalUid,
                            UUID.randomUUID().toString(),
                            topUpRequest!!
                        )
                    }

                    withContext(Dispatchers.Main) {
                        success.value = savingsGoalTransferResponse?.body()?.success
                    }
                }
            } else {
                // Handle error
            }
        }
    }
}