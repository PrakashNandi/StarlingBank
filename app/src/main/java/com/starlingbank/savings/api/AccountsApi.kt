package com.starlingbank.savings.api

import com.starlingbank.savings.models.AccountsResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Prakash Nandi on 25/08/2023.
 */
interface AccountsApi {

    @GET("api/v2/accounts")
    suspend fun getAccounts(): Response<AccountsResponse>
}