package com.partha.lokaljobportal.ui.bookmarks

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.partha.lokaljobportal.R
import com.partha.lokaljobportal.databinding.FragmentBookmarksBinding

class BookmarksFragment : Fragment() {
    private lateinit var binding: FragmentBookmarksBinding
    private val viewModel: BookmarksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false)


        return binding.root
    }
}