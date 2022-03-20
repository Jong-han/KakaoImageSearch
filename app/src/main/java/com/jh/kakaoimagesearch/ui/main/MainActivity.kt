package com.jh.kakaoimagesearch.ui.main

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.toAndroidXPair
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.jh.kakaoimagesearch.BR
import com.jh.kakaoimagesearch.R
import com.jh.kakaoimagesearch.base.BaseActivity
import com.jh.kakaoimagesearch.data.remote.response.Document
import com.jh.kakaoimagesearch.databinding.ActivityMainBinding
import com.jh.kakaoimagesearch.ext.hideKeyboard
import com.jh.kakaoimagesearch.ext.repeatOnStarted
import com.jh.kakaoimagesearch.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    companion object {
        const val EXTRA_DOCUMENT = "EXTRA_DOCUMENT"
    }

    override val viewModel: MainViewModel by viewModels()

    override val bindingVariable: Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    private val mainAdapter by lazy { MainAdapter(onClickItem) }

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

        repeatOnStarted {
            viewModel.searchString.debounce(2000)
                .collect {
                    if (it.isNotEmpty()) {
                        mainAdapter.refresh()
                        viewModel.getSearchResult(it)
                    } else {
                        mainAdapter.submitData(PagingData.empty())
                    }
                    hideKeyboard()
                    dataBinding.etSearch.clearFocus()
                }
        }

        repeatOnStarted {
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

    private val onClickItem: (View, Any)->Unit = { v: View, item: Any ->

        val pair: Pair<View, String> = Pair(v, v.transitionName)

        val optionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, pair.toAndroidXPair())

        Intent(this, DetailActivity::class.java).apply {
            putExtra(EXTRA_DOCUMENT, item as Document)
            startActivity(this, optionsCompat.toBundle())
        }
    }

}