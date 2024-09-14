package com.partha.lokaljobportal.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.partha.lokaljobportal.pojoClasses.ResultsItem

@Database(entities = [ResultsItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class ResultsDatabase : RoomDatabase() {

    abstract fun resultsItemDao(): ResultsItemDao

    companion object {
        @Volatile
        private var INSTANCE: ResultsDatabase? = null

        fun getDatabase(context: Context): ResultsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResultsDatabase::class.java,
                    "results_item_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
