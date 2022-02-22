package com.kakaobank.imageselector.ui

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.kakaobank.imageselector.R
import com.kakaobank.imageselector.common.BaseActivity
import com.kakaobank.imageselector.common.BaseFragmentPagerAdapter
import com.kakaobank.imageselector.databinding.ActivityMainBinding
import com.kakaobank.imageselector.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    private val viewModel: MainViewModel by viewModel()

    private val viewListFragment: ViewListFragment by inject()
    private val bookmarksFragment: BookmarksFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeView()
    }

    private fun initializeView() {
        setBottomNavigationView()
        setViewPager()
    }

    private fun setBottomNavigationView() {
        with (binding.bnvMenu) {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_view_list -> { binding.vpMain.currentItem = 0 }
                    R.id.menu_bookmarks -> { binding.vpMain.currentItem = 1 }
                }

                return@setOnItemSelectedListener true
            }
        }
    }

    private fun setViewPager() {
        BaseFragmentPagerAdapter(this).apply {
            addFragment(viewListFragment)
            addFragment(bookmarksFragment)

            with (binding.vpMain) {
                adapter = this@apply
//                isUserInputEnabled = false
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        binding.bnvMenu.menu.getItem(position).isChecked = true
                    }
                })
            }
        }
    }

}