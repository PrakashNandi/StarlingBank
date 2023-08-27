package com.starlingbank.savings.models

import com.starlingbank.savings.models.constants.CounterPartyType
import com.starlingbank.savings.models.constants.TransactionSource
import com.starlingbank.savings.models.constants.TransactionStatus
import java.util.Date

data class FeedItem(
    val feedItemUid: String,
    val categoryUid: String,
    val amount: Amount,
    val sourceAmount: Amount,
    val direction: TransactionDirection,
    val updatedAt: String,
    val transactionTime: Date,
    val settlementTime: Date,
    val source: TransactionSource,
    val status: TransactionStatus,
    val transactingApplicationUserUid: String,
    val counterPartyType: CounterPartyType,
    val counterPartyUid: String,
    val counterPartyName: String,
    val counterPartySubEntityUid: String,
    val counterPartySubEntityName: String,
    val counterPartySubEntityIdentifier: String,
    val counterPartySubEntitySubIdentifier: String,
    val reference: String,
    val country: String,
    val spendingCategory: String,
    val hasAttachment: Boolean,
    val hasReceipt: Boolean,
)