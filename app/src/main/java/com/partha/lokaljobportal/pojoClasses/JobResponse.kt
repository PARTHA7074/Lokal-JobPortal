package com.partha.lokaljobportal.pojoClasses

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.partha.lokaljobportal.room.Converters

@Parcelize
data class JobResponse(

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
) : Parcelable

@Parcelize
data class ContentV3(

	@field:SerializedName("V3")
	val v3: List<V3Item?>? = null
) : Parcelable

@Entity(tableName = "jobs")
@TypeConverters(Converters::class)
@Parcelize
data class ResultsItem(

	@field:SerializedName("updated_on")
	val updatedOn: String? = null,

	@field:SerializedName("other_details")
	val otherDetails: String? = null,

	@field:SerializedName("custom_link")
	val customLink: String? = null,

	@field:SerializedName("job_role")
	val jobRole: String? = null,

	@field:SerializedName("creatives")
	val creatives: List<CreativesItem?>? = null,

	@field:SerializedName("is_bookmarked")
	val isBookmarked: Boolean? = null,

	@field:SerializedName("expire_on")
	val expireOn: String? = null,

	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("job_hours")
	val jobHours: String? = null,

	@field:SerializedName("qualification")
	val qualification: Int? = null,

	@field:SerializedName("is_premium")
	val isPremium: Boolean? = null,

	@field:SerializedName("created_on")
	val createdOn: String? = null,

	@field:SerializedName("company_name")
	val companyName: String? = null,

	@field:SerializedName("job_location_slug")
	val jobLocationSlug: String? = null,

	@field:SerializedName("button_text")
	val buttonText: String? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("openings_count")
	val openingsCount: Int? = null,

	@field:SerializedName("primary_details")
	val primaryDetails: PrimaryDetails? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("is_applied")
	val isApplied: Boolean? = null,

	@field:SerializedName("content")
	val content: String? = null,

	@field:SerializedName("shares")
	val shares: Int? = null,

	@field:SerializedName("salary_min")
	val salaryMin: Int? = null,

	@field:SerializedName("fb_shares")
	val fbShares: Int? = null,

	@field:SerializedName("views")
	val views: Int? = null,

	@field:SerializedName("contact_preference")
	val contactPreference: ContactPreference? = null,

	@field:SerializedName("whatsapp_no")
	val whatsappNo: String? = null,

	@field:SerializedName("num_applications")
	val numApplications: Int? = null,

	@field:SerializedName("is_owner")
	val isOwner: Boolean? = null,

	@field:SerializedName("job_tags")
	val jobTags: List<JobTagsItem?>? = null,

	@field:SerializedName("salary_max")
	val salaryMax: Int? = null,

	@field:SerializedName("contentV3")
	val contentV3: ContentV3? = null,

	@field:SerializedName("job_category")
	val jobCategory: String? = null
) : Parcelable

@Parcelize
data class V3Item(

	@field:SerializedName("field_key")
	val fieldKey: String? = null,

	@field:SerializedName("field_value")
	val fieldValue: String? = null,

	@field:SerializedName("field_name")
	val fieldName: String? = null
) : Parcelable

@Parcelize
data class CreativesItem(

	@field:SerializedName("thumb_url")
	val thumbUrl: String? = null,

	@field:SerializedName("file")
	val file: String? = null,

	@field:SerializedName("creative_type")
	val creativeType: Int? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("order_id")
	val orderId: Int? = null
) : Parcelable

@Parcelize
data class JobTagsItem(

	@field:SerializedName("bg_color")
	val bgColor: String? = null,

	@field:SerializedName("text_color")
	val textColor: String? = null,

	@field:SerializedName("value")
	val value: String? = null
) : Parcelable

@Parcelize
data class ContactPreference(

	@field:SerializedName("preference")
	val preference: Int? = null,

	@field:SerializedName("preferred_call_start_time")
	val preferredCallStartTime: String? = null,

	@field:SerializedName("preferred_call_end_time")
	val preferredCallEndTime: String? = null,

	@field:SerializedName("whatsapp_link")
	val whatsappLink: String? = null
) : Parcelable

@Parcelize
data class PrimaryDetails(

	@field:SerializedName("Salary")
	val salary: String? = null,

	@field:SerializedName("Experience")
	val experience: String? = null,

	@field:SerializedName("Job_Type")
	val jobType: String? = null,

	@field:SerializedName("Fees_Charged")
	val feesCharged: String? = null,

	@field:SerializedName("Place")
	val place: String? = null
) : Parcelable
