package com.starlingbank.savings.repo

import com.starlingbank.savings.models.SavingsGoalRequest
import com.starlingbank.savings.models.SavingsGoalResponse
import com.starlingbank.savings.models.SavingsGoalTransferResponse
import com.starlingbank.savings.models.SavingsGoalsResponse
import com.starlingbank.savings.models.TopUpRequest
import retrofit2.Response

interface SavingsGoalRepo {

    suspend fun create(
        accountUid: String,
        savingsGoalRequest: SavingsGoalRequest
    ): Response<SavingsGoalResponse>

    suspend fun getSavingsGoals(accountUid: String): Response<SavingsGoalsResponse>

    suspend fun addMoney(
        accountUid: String,
        savingsGoalUid: String,
        transferUid: String,
        topUpRequest: TopUpRequest
    ): Response<SavingsGoalTransferResponse>
}