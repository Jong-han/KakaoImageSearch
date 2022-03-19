package com.jh.kakaoimagesearch.ui.main

import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.jh.kakaoimagesearch.BR
import com.jh.kakaoimagesearch.R
import com.jh.kakaoimagesearch.base.BaseActivity
import com.jh.kakaoimagesearch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override val bindingVariable: Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    private val mainAdapter by lazy { MainAdapter() }

    @OptIn(FlowPreview::class)
    override fun initViewAndEvent() {

        dataBinding.swipeView.setOnRefreshListener {
            mainAdapter.retry()
            dataBinding.swipeView.isRefreshing = false
        }

        dataBinding.rvImage.adapter = mainAdapter.also { adapter ->
            adapter.addLoadStateListener {
                if (it.refresh is LoadState.Error || it.append is LoadState.Error || it.prepend is LoadState.Error) {
                    dataBinding.swipeView.visibility = View.VISIBLE
                }
                else {
                    dataBinding.swipeView.visibility = View.INVISIBLE
                    emptyViewControl(mainAdapter.itemCount)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.searchString.debounce(2000)
                .collect {
                    if (it.isNotEmpty()) {
                        mainAdapter.refresh()
                        viewModel.getSearchResult(it)
                    } else {
                        mainAdapter.submitData(PagingData.empty())
                    }
                }
        }

        lifecycleScope.launch {
            viewModel.pagingData.collect {
                mainAdapter.submitData(it)
            }
        }
    }

    private fun emptyViewControl(itemCount: Int) {
        if (itemCount < 1) {
            dataBinding.tvEmpty.visibility = View.VISIBLE
            dataBinding.rvImage.visibility = View.INVISIBLE
        } else {
            dataBinding.tvEmpty.visibility = View.INVISIBLE
            dataBinding.rvImage.visibility = View.VISIBLE
        }
    }

}