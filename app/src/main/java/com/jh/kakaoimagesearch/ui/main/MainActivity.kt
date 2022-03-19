package com.jh.kakaoimagesearch.ui.main

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.jh.kakaoimagesearch.BR
import com.jh.kakaoimagesearch.R
import com.jh.kakaoimagesearch.base.BaseActivity
import com.jh.kakaoimagesearch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override val bindingVariable: Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    private val mainAdapter by lazy { MainAdapter() }

    @OptIn(FlowPreview::class)
    override fun initViewAndEvent() {

        dataBinding.rvImage.adapter = mainAdapter

        lifecycleScope.launch {
            viewModel.searchString.debounce(2000)
                .filter {
                    it.isNotEmpty()
                }.collect {
                    Toast.makeText(this@MainActivity, "검색을 시작합니다.", Toast.LENGTH_SHORT).show()
                    mainAdapter.refresh()
                    viewModel.getSearchResult(it)
                }
        }

        lifecycleScope.launch {
            viewModel.pagingData.collect {
                mainAdapter.submitData(it)
            }
        }

    }
}