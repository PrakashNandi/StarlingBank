package com.starlingbank.savings.repo

import com.starlingbank.savings.models.TransactionsResponse
import retrofit2.Response

/**
 * Created by Prakash Nandi on 25/08/2023.
 */
interface TransactionsRepo {

    suspend fun getTransactions(
        accountId: String,
        categoryId: String,
        minTransactionTimestamp: String,
        maxTransactionTimestamp: String
    ): Response<TransactionsResponse>
}