package com.partha.lokaljobportal.ui.jobs

import androidx.lifecycle.ViewModel
import com.partha.lokaljobportal.retrofit.RetrofitClient

class JobsViewModel : ViewModel() {
    private val apiService = RetrofitClient.apiService
    suspend fun getJobs() = apiService.getJobs(1)


}