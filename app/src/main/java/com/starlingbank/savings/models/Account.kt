package com.starlingbank.savings.models

import com.starlingbank.savings.models.constants.AccountType

/**
 * Created by Prakash Nandi on 25/08/2023.
 */
data class Account(
    val accountUid: String,
    val accountType: AccountType,
    val defaultCategory: String,
    val currency: String,
    val createdAt: String,
    val name: String
)