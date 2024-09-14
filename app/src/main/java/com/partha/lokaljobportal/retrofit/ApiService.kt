package com.partha.lokaljobportal.retrofit

import com.partha.lokaljobportal.pojoClasses.JobResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("common/jobs")
    fun getJobs(@Query("page") page: Int): Call<JobResponse>
}
