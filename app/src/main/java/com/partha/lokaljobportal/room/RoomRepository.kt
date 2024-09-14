package com.partha.lokaljobportal.room

import android.app.Application
import android.content.Context
import com.partha.lokaljobportal.pojoClasses.ResultsItem

class RoomRepository(context : Context) {
    private val resultsItemDao = ResultsDatabase.getDatabase(context).resultsItemDao()

    // Function to insert a single ResultsItem into Room
    suspend fun insertResultItem(resultsItem: ResultsItem) {
        resultsItemDao.insertResultsItem(resultsItem)
    }

    // Function to retrieve all ResultsItems from Room
    suspend fun getAllResultsItems(): List<ResultsItem> {
        return resultsItemDao.getAllResultsItems()
    }

    // Function to retrieve a single ResultsItem by its id
    suspend fun getResultItemById(id: Int): ResultsItem? {
        return resultsItemDao.getResultItemById(id)
    }

    // Function to delete a ResultsItem by its id
    suspend fun deleteResultItemById(id: Int) {
        resultsItemDao.deleteResultsItemById(id)
    }

}
