package com.partha.lokaljobportal.ui.jobDetails

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.partha.lokaljobportal.R
import com.partha.lokaljobportal.databinding.FragmentJobDetailsBinding
import com.partha.lokaljobportal.pojoClasses.ResultsItem

class JobDetailsFragment : Fragment() {

    private lateinit var binding: FragmentJobDetailsBinding
    private val viewModel: JobDetailsViewModel by viewModels()
    private lateinit var job: ResultsItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobDetailsBinding.inflate(inflater, container, false)

        arguments?.let {
            job = JobDetailsFragmentArgs.fromBundle(it).job
        }


        return binding.root
    }
}