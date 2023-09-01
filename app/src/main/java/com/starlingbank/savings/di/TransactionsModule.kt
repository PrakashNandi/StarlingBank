package com.starlingbank.savings.di

import com.starlingbank.savings.api.TransactionsApi
import com.starlingbank.savings.managers.TransactionsManager
import com.starlingbank.savings.managers.impl.TransactionsManagerImpl
import com.starlingbank.savings.repo.TransactionsRepo
import com.starlingbank.savings.repo.impl.TransactionsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TransactionsModule {

    @Provides
    @Singleton
    fun provideTransactionsApi(retrofit: Retrofit): TransactionsApi =
        retrofit.create(TransactionsApi::class.java)

    @Provides
    @Singleton
    fun provideTransactionsRepo(
        transactionsRepoImpl: TransactionsRepoImpl
    ): TransactionsRepo = transactionsRepoImpl

    @Provides
    @Singleton
    fun provideTransactionsManager(
        transactionsManagerImpl: TransactionsManagerImpl
    ): TransactionsManager = transactionsManagerImpl
}