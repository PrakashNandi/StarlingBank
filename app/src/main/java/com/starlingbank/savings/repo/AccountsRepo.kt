package com.starlingbank.savings.repo

import com.starlingbank.savings.models.AccountsResponse
import retrofit2.Response

/**
 * Created by Prakash Nandi on 25/08/2023.
 */
interface AccountsRepo {

    suspend fun getAccounts(): Response<AccountsResponse>
}