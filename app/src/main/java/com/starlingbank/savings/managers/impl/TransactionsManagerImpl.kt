package com.starlingbank.savings.managers.impl

import com.starlingbank.savings.managers.TransactionsManager
import com.starlingbank.savings.models.TransactionsResponse
import com.starlingbank.savings.repo.TransactionsRepo
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class TransactionsManagerImpl @Inject constructor(private val transactionsRepo: TransactionsRepo) :
    TransactionsManager {

    override suspend fun getLastSevenDaysTransactions(
        accountId: String,
        categoryId: String
    ): Response<TransactionsResponse> {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val toDate = Date()
        val toDateStr: String = dateFormat.format(toDate)

        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -7)
        val fromDate: Date = cal.time
        val fromDateStr: String = dateFormat.format(fromDate)
        return transactionsRepo.getTransactions(accountId, categoryId, fromDateStr, toDateStr)
    }
}