package com.partha.lokaljobportal.ui.jobs

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.partha.lokaljobportal.R
import com.partha.lokaljobportal.databinding.FragmentJobsBinding

class JobsFragment : Fragment() {
    private lateinit var binding: FragmentJobsBinding
    private val viewModel: JobsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobsBinding.inflate(inflater, container, false)


        return binding.root
    }
}