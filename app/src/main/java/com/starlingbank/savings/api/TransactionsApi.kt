package com.starlingbank.savings.api

import com.starlingbank.savings.models.TransactionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Prakash Nandi on 25/08/2023.
 */
interface TransactionsApi {

    @GET("api/v2/feed/account/{accountUid}/category/{categoryUid}/transactions-between")
    suspend fun getTransactionsByAccountId(
        @Path("accountUid") accountUid: String,
        @Path("categoryUid") categoryUid: String,
        @Query("minTransactionTimestamp") minTransactionTimestamp: String,
        @Query("maxTransactionTimestamp") maxTransactionTimestamp: String
    ): Response<TransactionsResponse>
}