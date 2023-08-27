package com.starlingbank.savings.models

class SavingsGoalRequest(
    val name: String,
    val currency: String,
    val target: Amount,
    val base64EncodedPhoto: String
)