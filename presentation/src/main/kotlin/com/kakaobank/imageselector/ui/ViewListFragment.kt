package com.kakaobank.imageselector.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kakaobank.imageselector.PagingExceptionType
import com.kakaobank.imageselector.R
import com.kakaobank.imageselector.common.BaseFragment
import com.kakaobank.imageselector.databinding.FragmentViewListBinding
import com.kakaobank.imageselector.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ViewListFragment : BaseFragment<FragmentViewListBinding>(R.layout.fragment_view_list) {

    private val viewModel: MainViewModel by sharedViewModel()

    private val viewListAdapter: ViewListAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = this.viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initializeView()
    }

    private fun initializeView() {
        initializeSearchBar()
        initializeResultListAdapter()
    }

    private fun initializeSearchBar() {
        with(binding.svSearch) {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewListAdapter.submitData(lifecycle, PagingData.empty())
                    viewModel.requestImageList(query ?: "")
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun initializeResultListAdapter() {
        with(binding.rvList) {
            adapter = viewListAdapter.apply {
                addLoadStateListener {
                    setLoadState(it)
                }
                onItemClick = { position ->
                    AlertDialog.Builder(requireContext()).apply {
                        snapshot()[position]?.let {
                            setMessage(R.string.add_bookmark)
                            setPositiveButton(R.string.confirm) { _, _ ->
                                if (!viewModel.addBookmark(it)) {
                                    Toast.makeText(requireContext(), R.string.already_bookmarked, Toast.LENGTH_LONG).show()
                                }
                            }
                            setNegativeButton((R.string.cancel)) { dialog, _ ->
                                dialog.dismiss()
                            }
                        }
                    }.show()
                }
            }
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

    private fun setLoadState(state: CombinedLoadStates) {
        binding.isLoading = when {
            state.append is LoadState.Loading -> true
            state.refresh is LoadState.Loading -> true
            else -> false
        }
        val error = when {
            state.append is LoadState.Error -> state.append as LoadState.Error
            state.refresh is LoadState.Error -> state.refresh as LoadState.Error
            else -> null
        }
        error?.let {
            when (it.error.message) {
                PagingExceptionType.TYPE_NO_RESULT -> {
                    viewListAdapter.submitData(lifecycle, PagingData.empty())
                }
            }
            Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_SHORT).show()
        }
    }

}