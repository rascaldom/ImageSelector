package com.kakaobank.imageselector.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kakaobank.imageselector.R
import com.kakaobank.imageselector.common.BaseFragment
import com.kakaobank.imageselector.databinding.FragmentBookmarksBinding
import com.kakaobank.imageselector.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BookmarksFragment : BaseFragment<FragmentBookmarksBinding>(R.layout.fragment_bookmarks) {

    private val viewModel: MainViewModel by sharedViewModel()

    private val bookmarksAdapter: BookmarksAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeView()
    }

    private fun initializeObserver() {
        lifecycleScope.launch {
            viewModel.bookmarksList.collect {
                bookmarksAdapter.submitList(it.values.toList())
            }
        }
    }

    private fun initializeView() {
        initializeBookmarksListAdapter()
    }

    private fun initializeBookmarksListAdapter() {
        with(binding.rvList) {
            adapter = bookmarksAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

}