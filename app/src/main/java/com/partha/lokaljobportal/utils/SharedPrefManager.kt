package com.partha.lokaljobportal.utils

import android.content.Context

class SharedPrefManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("bookmark_prefs", Context.MODE_PRIVATE)

    // Save the bookmark status for a specific job
    fun saveBookmarkStatus(jobId: Int, isBookmarked: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("is_bookmarked_$jobId", isBookmarked)
        editor.apply()
    }

    // Retrieve the bookmark status for a specific job
    fun getBookmarkStatus(jobId: Int): Boolean {
        return sharedPreferences.getBoolean("is_bookmarked_$jobId", false)
    }
}
