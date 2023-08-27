package com.starlingbank.savings.repo.impl

import com.starlingbank.savings.api.TransactionsApi
import com.starlingbank.savings.models.TransactionsResponse
import com.starlingbank.savings.repo.TransactionsRepo
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Prakash Nandi on 25/08/2023.
 */
class TransactionsRepoImpl @Inject constructor(private val transactionsApi: TransactionsApi) :
    TransactionsRepo {

    override suspend fun getTransactions(
        accountId: String,
        categoryId: String,
        minTransactionTimestamp: String,
        maxTransactionTimestamp: String
    ): Response<TransactionsResponse> {
        return transactionsApi.getTransactionsByAccountId(
            accountId,
            categoryId,
            minTransactionTimestamp,
            maxTransactionTimestamp
        )
    }
}