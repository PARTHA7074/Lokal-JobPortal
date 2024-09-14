package com.partha.lokaljobportal.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.partha.lokaljobportal.pojoClasses.ResultsItem

@Dao
interface ResultsItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResultsItem(resultsItem: ResultsItem)

    @Query("DELETE FROM jobs WHERE id = :id")
    suspend fun deleteResultsItemById(id: Int)

    @Query("SELECT * FROM jobs")
    suspend fun getAllResultsItems(): List<ResultsItem>

    @Query("SELECT * FROM jobs WHERE id = :id")
    suspend fun getResultItemById(id: Int): ResultsItem?

}
