package com.starlingbank.savings.di

import com.starlingbank.savings.api.AccountsApi
import com.starlingbank.savings.api.ApiConstants
import com.starlingbank.savings.api.SavingsGoalsApi
import com.starlingbank.savings.api.TransactionsApi
import com.starlingbank.savings.managers.TransactionsManager
import com.starlingbank.savings.managers.impl.TransactionsManagerImpl
import com.starlingbank.savings.repo.AccountsRepo
import com.starlingbank.savings.repo.SavingsGoalRepo
import com.starlingbank.savings.repo.TransactionsRepo
import com.starlingbank.savings.repo.impl.AccountsRepoImpl
import com.starlingbank.savings.repo.impl.SavingsGoalRepoImpl
import com.starlingbank.savings.repo.impl.TransactionsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by Prakash Nandi on 25/08/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
class AccountsModule {

    @Provides
    @Singleton
    fun provideAccountsApi(retrofit: Retrofit): AccountsApi =
        retrofit.create(AccountsApi::class.java)

    @Provides
    @Singleton
    fun provideAccountsRepo(
        accountsRepoImpl: AccountsRepoImpl
    ): AccountsRepo = accountsRepoImpl
}