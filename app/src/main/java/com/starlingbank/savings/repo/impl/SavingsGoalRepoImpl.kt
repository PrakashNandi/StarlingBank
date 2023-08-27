package com.starlingbank.savings.repo.impl

import com.starlingbank.savings.api.SavingsGoalsApi
import com.starlingbank.savings.models.SavingsGoalRequest
import com.starlingbank.savings.models.SavingsGoalResponse
import com.starlingbank.savings.models.SavingsGoalTransferResponse
import com.starlingbank.savings.models.SavingsGoalsResponse
import com.starlingbank.savings.models.TopUpRequest
import com.starlingbank.savings.repo.SavingsGoalRepo
import retrofit2.Response
import javax.inject.Inject

class SavingsGoalRepoImpl @Inject constructor(private val savingsGoalsApi: SavingsGoalsApi) :
    SavingsGoalRepo {

    override suspend fun create(
        accountUid: String,
        savingsGoalRequest: SavingsGoalRequest
    ): Response<SavingsGoalResponse> {
        return savingsGoalsApi.createNewSavingsGoal(accountUid, savingsGoalRequest)
    }

    override suspend fun getSavingsGoals(accountUid: String): Response<SavingsGoalsResponse> {
        return savingsGoalsApi.getSavingsGoals(accountUid)
    }

    override suspend fun addMoney(
        accountUid: String,
        savingsGoalUid: String,
        transferUid: String,
        topUpRequest: TopUpRequest
    ): Response<SavingsGoalTransferResponse> {
        return savingsGoalsApi.addMoney(accountUid, savingsGoalUid, transferUid, topUpRequest)
    }
}