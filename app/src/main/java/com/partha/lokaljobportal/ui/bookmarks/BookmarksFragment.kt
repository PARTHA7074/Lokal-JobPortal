package com.partha.lokaljobportal.ui.bookmarks

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.partha.lokaljobportal.R
import com.partha.lokaljobportal.adapters.JobsRecyclerAdapter
import com.partha.lokaljobportal.databinding.FragmentBookmarksBinding
import com.partha.lokaljobportal.pojoClasses.ResultsItem

class BookmarksFragment : Fragment() {
    private lateinit var binding: FragmentBookmarksBinding
    private val viewModel: BookmarksViewModel by viewModels()
    private lateinit var jobAdapter: JobsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false)


        jobAdapter = JobsRecyclerAdapter(isBookmarksFragment = true, onJobClick = { job ->


        }, onBookmarkClick = { isBookmarked, job ->
            if (isBookmarked) job?.let { viewModel.insertResultItem(it) }
            else job?.id?.let { viewModel.deleteResultItemById(it) }
            binding.emptyText.isVisible = jobAdapter.itemCount == 1
        })

        viewModel.fetchBookmarks()
        viewModel.jobs.observe(viewLifecycleOwner) { jobResponse ->
            binding.emptyText.isVisible = jobResponse?.isEmpty() == true
            jobAdapter.setJobs(jobResponse ?: emptyList())
            binding.jobsRecyclerView.adapter = jobAdapter
        }

        return binding.root
    }
}