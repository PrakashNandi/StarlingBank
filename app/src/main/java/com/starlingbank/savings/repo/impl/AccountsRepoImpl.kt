package com.starlingbank.savings.repo.impl

import com.starlingbank.savings.api.AccountsApi
import com.starlingbank.savings.models.AccountsResponse
import com.starlingbank.savings.repo.AccountsRepo
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Prakash Nandi on 25/08/2023.
 */
class AccountsRepoImpl @Inject constructor(private val accountsApi: AccountsApi) : AccountsRepo {

    override suspend fun getAccounts(): Response<AccountsResponse> {
        return accountsApi.getAccounts()
    }
}