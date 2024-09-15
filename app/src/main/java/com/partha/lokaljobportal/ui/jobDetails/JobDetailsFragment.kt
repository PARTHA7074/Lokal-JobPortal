package com.partha.lokaljobportal.ui.jobDetails

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.partha.lokaljobportal.R
import com.partha.lokaljobportal.databinding.FragmentJobDetailsBinding
import com.partha.lokaljobportal.pojoClasses.ResultsItem
import com.partha.lokaljobportal.utils.SharedPrefManager

class JobDetailsFragment : Fragment() {

    private lateinit var binding: FragmentJobDetailsBinding
    private val viewModel: JobDetailsViewModel by viewModels()
    private lateinit var job: ResultsItem
    private val sharedPrefManager by lazy { SharedPrefManager(requireContext()) }
    private var isBookmarked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobDetailsBinding.inflate(inflater, container, false)
        setBottomNavigationVisibility(false)

        arguments?.let {
            job = JobDetailsFragmentArgs.fromBundle(it).job
        }

        isBookmarked = job.id?.let { it1 -> sharedPrefManager.getBookmarkStatus(it1) }?: false

        bindJobDetails()

        binding.btnApply.setOnClickListener {
            if (job.contactPreference?.whatsappLink?.isNotEmpty() == true) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(job.contactPreference!!.whatsappLink))
                binding.root.context.startActivity(intent)
            }
        }

        binding.callBtn.setOnClickListener {
            if (job.customLink?.isNotEmpty() == true) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(job.customLink))
                binding.root.context.startActivity(intent)
            }
        }

        binding.bookmarkBtn.setOnClickListener {
            if (job.jobRole != null && job.id != null) {
                isBookmarked = !isBookmarked
                sharedPrefManager.saveBookmarkStatus(job.id!!, isBookmarked)

                binding.bookmarkBtn.setImageResource(
                    if (isBookmarked) R.drawable.baseline_bookmark_24
                    else R.drawable.outline_bookmark_border_24
                )

                if (isBookmarked) viewModel.insertResultItem(job)
                else viewModel.deleteResultItemById(job.id!!)

                Toast.makeText(binding.root.context, "${if (isBookmarked) "Job saved" else "Job removed"}", Toast.LENGTH_SHORT).show()
            }  else Toast.makeText(binding.root.context, "This Job is invalid", Toast.LENGTH_SHORT).show()
        }


        binding.closeBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    // Method to bind the job details to the UI
    private fun bindJobDetails() {
        binding.tvJobTitle.text = job.jobRole ?: "N/A"
        binding.tvCompanyName.text = job.companyName?: "N/A"
        binding.tvJobLocation.text = job.jobLocationSlug ?: "N/A"
        binding.tvSalary.text = "₹${job.salaryMin} - ₹${job.salaryMax} per month"
        binding.tvJobType.text = job.jobHours ?: "N/A"
        binding.jobDescription.text = job.title ?: "No description available"
        binding.tvJobCategory.text = job.jobCategory ?: "N/A"
        binding.tvJobExperience.text = job.primaryDetails?.experience ?: "N/A"
        binding.tvJobQualification.text = job.primaryDetails?.qualification ?: "N/A"
        binding.callBtn.text = job.buttonText ?: "Call HR"

        if (job.salaryMin == null && job.salaryMax == null) {
            binding.tvSalary.text = "Not disclosed"
        } else if (job.salaryMin == null) {
            binding.tvSalary.text = "Upto ₹${job.salaryMax} per month"
        } else if (job.salaryMax == null) {
            binding.tvSalary.text = "From ₹${job.salaryMin} per month"
        }

        binding.bookmarkBtn.setImageResource(
            if (isBookmarked) R.drawable.baseline_bookmark_24
            else R.drawable.outline_bookmark_border_24
        )

        if (job.jobTags?.isNotEmpty() == true){
            binding.jobTag1.apply {
                isVisible = true
                text = job.jobTags!![0]?.value
                setTextColor(Color.parseColor(job.jobTags!![0]?.textColor))
                setBackgroundColor(Color.parseColor(job.jobTags!![0]?.bgColor))
            }
        }
        if (job.jobTags?.size == 2){
            binding.jobTag2.apply {
                isVisible = true
                text = job.jobTags!![1]?.value
                setTextColor(Color.parseColor(job.jobTags!![1]?.textColor))
                setBackgroundColor(Color.parseColor(job.jobTags!![1]?.bgColor))
            }
        }

    }

    private fun setBottomNavigationVisibility(isVisible: Boolean) {
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav?.isVisible = isVisible
    }

    override fun onDestroy() {
        super.onDestroy()
        setBottomNavigationVisibility(true)
    }
}
