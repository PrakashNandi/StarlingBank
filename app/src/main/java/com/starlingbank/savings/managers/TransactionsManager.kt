package com.starlingbank.savings.managers

import com.starlingbank.savings.models.TransactionsResponse
import retrofit2.Response

interface TransactionsManager {

    suspend fun getLastSevenDaysTransactions(
        accountId: String,
        categoryId: String
    ): Response<TransactionsResponse>
}