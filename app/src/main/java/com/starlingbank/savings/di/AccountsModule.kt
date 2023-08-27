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
    fun provideBaseUrl() = ApiConstants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", ApiConstants.ACCESS_TOKEN)
                .build()
            chain.proceed(request)
        }

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(interceptor)

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideAccountsApi(retrofit: Retrofit): AccountsApi =
        retrofit.create(AccountsApi::class.java)

    @Provides
    @Singleton
    fun provideTransactionsApi(retrofit: Retrofit): TransactionsApi =
        retrofit.create(TransactionsApi::class.java)

    @Provides
    @Singleton
    fun provideSavingsGoalsApi(retrofit: Retrofit): SavingsGoalsApi =
        retrofit.create(SavingsGoalsApi::class.java)

    @Provides
    @Singleton
    fun provideAccountsRepo(
        accountsRepoImpl: AccountsRepoImpl
    ): AccountsRepo = accountsRepoImpl

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

    @Provides
    @Singleton
    fun provideSavingsGoalRepo(
        savingsGoalRepoImpl: SavingsGoalRepoImpl
    ): SavingsGoalRepo = savingsGoalRepoImpl
}