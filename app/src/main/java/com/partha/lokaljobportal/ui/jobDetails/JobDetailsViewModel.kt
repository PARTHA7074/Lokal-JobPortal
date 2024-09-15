package com.partha.lokaljobportal.ui.jobDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.partha.lokaljobportal.pojoClasses.ResultsItem
import com.partha.lokaljobportal.room.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JobDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val roomRepository = RoomRepository(application)
    var jobs = MutableLiveData<List<ResultsItem?>?>()

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