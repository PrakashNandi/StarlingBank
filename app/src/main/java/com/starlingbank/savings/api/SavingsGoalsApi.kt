package com.starlingbank.savings.api

import com.starlingbank.savings.models.SavingsGoalRequest
import com.starlingbank.savings.models.SavingsGoalResponse
import com.starlingbank.savings.models.SavingsGoalTransferResponse
import com.starlingbank.savings.models.SavingsGoalsResponse
import com.starlingbank.savings.models.TopUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface SavingsGoalsApi {

    @PUT("/api/v2/account/{accountUid}/savings-goals")
    suspend fun createNewSavingsGoal(
        @Path("accountUid") accountUid: String,
        @Body savingsGoalRequest: SavingsGoalRequest
    ): Response<SavingsGoalResponse>

    @GET("/api/v2/account/{accountUid}/savings-goals")
    suspend fun getSavingsGoals(@Path("accountUid") accountUid: String): Response<SavingsGoalsResponse>

    @PUT("/api/v2/account/{accountUid}/savings-goals/{savingsGoalUid}/add-money/{transferUid}")
    suspend fun addMoney(
        @Path("accountUid") accountUid: String,
        @Path("savingsGoalUid") savingsGoalUid: String,
        @Path("transferUid") transferUid: String,
        @Body topUpRequest: TopUpRequest
    ): Response<SavingsGoalTransferResponse>
}