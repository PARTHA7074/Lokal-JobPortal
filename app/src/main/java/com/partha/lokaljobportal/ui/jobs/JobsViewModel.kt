package com.partha.lokaljobportal.ui.jobs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.partha.lokaljobportal.pojoClasses.JobResponse
import com.partha.lokaljobportal.pojoClasses.ResultsItem
import com.partha.lokaljobportal.retrofit.RetrofitRepository
import com.partha.lokaljobportal.room.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobsViewModel (application: Application) : AndroidViewModel(application) {
    private val retrofitRepository = RetrofitRepository()
    private val roomRepository = RoomRepository(application)
    var jobs = MutableLiveData<JobResponse>()
    var errorMessage = MutableLiveData<String>()

    fun fetchJobs(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            retrofitRepository.getJobs(page).enqueue(object : Callback<JobResponse>{
                override fun onResponse(call: Call<JobResponse>, response: Response<JobResponse>) {
                    if (response.isSuccessful) {
                        jobs.value = response.body()
                    } else {
                        errorMessage.value = "Error: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<JobResponse>, t: Throwable) {
                    errorMessage.value = "Exception: ${t.localizedMessage}"
                }

            })
        }
    }


    // Function to delete a result item by id
    fun deleteResultItemById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepository.deleteResultItemById(id)
        }
    }

    // Function to insert a result item
    fun insertResultItem(resultsItem: ResultsItem) {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepository.insertResultItem(resultsItem)
        }
    }


}