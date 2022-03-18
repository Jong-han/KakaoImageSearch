package com.jh.kakaoimagesearch.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<T: ViewDataBinding, V: ViewModel>: AppCompatActivity() {

    lateinit var dataBinding: T
    abstract val viewModel: V
    abstract val bindingVariable: Int?
    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract fun initViewAndEvent()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<T>( this, getLayoutId() ).apply {

            dataBinding = this
            lifecycleOwner = this@BaseActivity
            bindingVariable?.let { setVariable( it, viewModel ) }

        }
        initViewAndEvent()

    }

}