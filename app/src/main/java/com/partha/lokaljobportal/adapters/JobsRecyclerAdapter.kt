package com.partha.lokaljobportal.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partha.lokaljobportal.R
import com.partha.lokaljobportal.databinding.JobItemBinding
import com.partha.lokaljobportal.pojoClasses.ResultsItem
import com.partha.lokaljobportal.utils.SharedPrefManager

class JobsRecyclerAdapter(
    private var jobsList: MutableList<ResultsItem?> = ArrayList(),
    private val onJobClick: (ResultsItem?) -> Unit,
    private val onBookmarkClick: (Boolean, ResultsItem?) -> Unit
) : RecyclerView.Adapter<JobsRecyclerAdapter.JobViewHolder>() {

    inner class JobViewHolder(private val binding: JobItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val sharedPrefManager = SharedPrefManager(binding.root.context)

        fun bind(job: ResultsItem?) {
            job?.let {
                var isBookmarked = job.id?.let { it1 -> sharedPrefManager.getBookmarkStatus(it1) }?: false
                binding.bookmarkButton.setImageResource(
                    if (isBookmarked) R.drawable.baseline_bookmark_24
                    else R.drawable.outline_bookmark_border_24
                )

                binding.jobTitle.text = it.jobRole ?: "N/A"
                binding.jobLocation.text = it.jobLocationSlug ?: "N/A"
                binding.jobSalary.text = "${it.salaryMin} - ${it.salaryMax} monthly"
                binding.jobPhone.text = it.buttonText ?: "N/A"

                if (it.salaryMin == null && it.salaryMax == null) {
                    binding.jobSalary.text = "Not disclosed"
                } else if (it.salaryMin == null) {
                    binding.jobSalary.text = "Upto ${it.salaryMax} monthly"
                } else if (it.salaryMax == null) {
                    binding.jobSalary.text = "From ${it.salaryMin} monthly"
                }

                binding.jobPhone.setOnClickListener {
                    if (job.customLink?.isNotEmpty() == true) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(job.customLink))
                        binding.root.context.startActivity(intent)
                    }
                }
                binding.jobWhatsApp.setOnClickListener {
                    if (job.contactPreference?.whatsappLink?.isNotEmpty() == true) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(job.contactPreference.whatsappLink))
                        binding.root.context.startActivity(intent)
                    }
                }
                binding.root.setOnClickListener { onJobClick(job) }
                binding.bookmarkButton.setOnClickListener {
                    isBookmarked = !isBookmarked
                    job.id?.let { it1 -> sharedPrefManager.saveBookmarkStatus(it1, isBookmarked) }
                    onBookmarkClick(isBookmarked, job)

                    binding.bookmarkButton.setImageResource(
                        if (isBookmarked) R.drawable.baseline_bookmark_24
                        else R.drawable.outline_bookmark_border_24
                    )

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        return JobViewHolder(JobItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(jobsList[position])
    }

    override fun getItemCount(): Int = jobsList.size

    // Method to add jobs to the list (pagination support)
    fun addJobs(newJobs: List<ResultsItem?>) {
        jobsList.addAll(newJobs)
        notifyItemRangeInserted(jobsList.size - newJobs.size, newJobs.size)
    }

}
