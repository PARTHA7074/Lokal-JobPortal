package com.partha.lokaljobportal.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.partha.lokaljobportal.pojoClasses.ContactPreference
import com.partha.lokaljobportal.pojoClasses.ContentV3
import com.partha.lokaljobportal.pojoClasses.CreativesItem
import com.partha.lokaljobportal.pojoClasses.JobTagsItem
import com.partha.lokaljobportal.pojoClasses.PrimaryDetails

class Converters {

    // CreativesItem Converter
    @TypeConverter
    fun fromCreativesList(value: List<CreativesItem?>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCreativesList(value: String): List<CreativesItem?>? {
        val listType = object : TypeToken<List<CreativesItem?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    // JobTagsItem Converter
    @TypeConverter
    fun fromJobTagsList(value: List<JobTagsItem?>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toJobTagsList(value: String): List<JobTagsItem?>? {
        val listType = object : TypeToken<List<JobTagsItem?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    // ContentV3 Converter
    @TypeConverter
    fun fromContentV3(contentV3: ContentV3?): String? {
        return Gson().toJson(contentV3)
    }

    @TypeConverter
    fun toContentV3(contentV3String: String?): ContentV3? {
        val type = object : TypeToken<ContentV3>() {}.type
        return Gson().fromJson(contentV3String, type)
    }

    // ContactPreference Converter
    @TypeConverter
    fun fromContactPreference(contactPreference: ContactPreference?): String? {
        return Gson().toJson(contactPreference)
    }

    @TypeConverter
    fun toContactPreference(contactPreferenceString: String?): ContactPreference? {
        val type = object : TypeToken<ContactPreference>() {}.type
        return Gson().fromJson(contactPreferenceString, type)
    }

    // PrimaryDetails Converter
    @TypeConverter
    fun fromPrimaryDetails(primaryDetails: PrimaryDetails?): String? {
        return Gson().toJson(primaryDetails)
    }

    @TypeConverter
    fun toPrimaryDetails(primaryDetailsString: String?): PrimaryDetails? {
        val type = object : TypeToken<PrimaryDetails>() {}.type
        return Gson().fromJson(primaryDetailsString, type)
    }

}
