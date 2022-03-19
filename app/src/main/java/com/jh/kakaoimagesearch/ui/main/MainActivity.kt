package com.jh.kakaoimagesearch.ui.main

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.jh.kakaoimagesearch.BR
import com.jh.kakaoimagesearch.R
import com.jh.kakaoimagesearch.base.BaseActivity
import com.jh.kakaoimagesearch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override val bindingVariable: Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    private val mainAdapter by lazy { MainAdapter() }

    override fun initViewAndEvent() {

        dataBinding.rvImage.adapter = mainAdapter

        lifecycleScope.launch {
            viewModel.getSearchResult().collectLatest {
                mainAdapter.submitData(it)
            }
        }

    }
}