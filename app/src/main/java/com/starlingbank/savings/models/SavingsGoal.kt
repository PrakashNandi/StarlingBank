package com.starlingbank.savings.models

import com.starlingbank.savings.models.constants.SavingsGoalState

class SavingsGoal(
    val savingsGoalUid: String,
    val name: String,
    val target: Amount,
    val totalSaved: Amount,
    val savedPercentage: Int,
    val state: SavingsGoalState
)