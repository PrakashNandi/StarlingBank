package com.starlingbank.savings.di

import com.starlingbank.savings.api.SavingsGoalsApi
import com.starlingbank.savings.repo.SavingsGoalRepo
import com.starlingbank.savings.repo.impl.SavingsGoalRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SavingsGoalModule {

    @Provides
    @Singleton
    fun provideSavingsGoalsApi(retrofit: Retrofit): SavingsGoalsApi =
        retrofit.create(SavingsGoalsApi::class.java)

    @Provides
    @Singleton
    fun provideSavingsGoalRepo(
        savingsGoalRepoImpl: SavingsGoalRepoImpl
    ): SavingsGoalRepo = savingsGoalRepoImpl
}