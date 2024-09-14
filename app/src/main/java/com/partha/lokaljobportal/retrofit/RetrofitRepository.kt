package com.partha.lokaljobportal.retrofit

import com.partha.lokaljobportal.pojoClasses.JobResponse
import retrofit2.Call

class RetrofitRepository {
    private val apiService = RetrofitClient.apiService

    fun getJobs(page: Int): Call<JobResponse> {
        return apiService.getJobs(page)
    }

}