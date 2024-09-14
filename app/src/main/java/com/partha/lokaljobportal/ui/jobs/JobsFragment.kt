package com.partha.lokaljobportal.ui.jobs

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partha.lokaljobportal.adapters.JobsRecyclerAdapter
import com.partha.lokaljobportal.databinding.FragmentJobsBinding

class JobsFragment : Fragment() {
    private lateinit var binding: FragmentJobsBinding
    private val viewModel: JobsViewModel by viewModels()
    private lateinit var jobAdapter: JobsRecyclerAdapter
    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobsBinding.inflate(inflater, container, false)

        jobAdapter = JobsRecyclerAdapter(onJobClick = { job ->


        }, onBookmarkClick = { isBookmarked, job ->
            if (isBookmarked) job?.let { viewModel.insertResultItem(it) }
            else job?.id?.let { viewModel.deleteResultItemById(it) }
        })

        binding.jobsRecyclerView.apply {
            adapter = jobAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    // Trigger next page load when scrolled to the end
                    if (!isLoading && !isLastPage) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                            setLoading(true)
                            viewModel.fetchJobs(currentPage++)
                        }
                    }
                }
            })
        }

        if (viewModel.jobs.value == null) {
            setLoading(true)
            viewModel.fetchJobs(currentPage++)
        }
        viewModel.jobs.observe(viewLifecycleOwner) { jobResponse ->
            jobAdapter.addJobs(jobResponse.results?: emptyList())
            setLoading(false)
            isLastPage = jobResponse.results?.isEmpty() == true
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            setLoading(false)
        }

        return binding.root
    }

    private fun setLoading(loading: Boolean){
        binding.progressBar.isVisible = loading
        isLoading = loading
    }
}