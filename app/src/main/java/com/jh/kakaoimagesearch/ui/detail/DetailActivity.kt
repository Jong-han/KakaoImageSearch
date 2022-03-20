package com.jh.kakaoimagesearch.ui.detail

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.activity.viewModels
import com.jh.kakaoimagesearch.BR
import com.jh.kakaoimagesearch.R
import com.jh.kakaoimagesearch.base.BaseActivity
import com.jh.kakaoimagesearch.databinding.ActivityDetailBinding
import com.jh.kakaoimagesearch.ext.load
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    override val viewModel: DetailViewModel by viewModels()

    override val bindingVariable: Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_detail

    private var moveX = 0f
    private var moveY = 0f
    private var startY = 0f

    private var isShowingInfo = false

    private val coordinator: Pair<Float, Float> by lazy { dataBinding.ivImage.x to dataBinding.ivImage.y }

    @SuppressLint("ClickableViewAccessibility")
    override fun initViewAndEvent() {

        supportPostponeEnterTransition()
        viewModel.document?.let { doc ->
            dataBinding.ivImage.load(doc.image_url) {
                supportStartPostponedEnterTransition()
            }
        }

        dataBinding.ivImage.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    moveX = coordinator.first - motionEvent.rawX
                    moveY = coordinator.second - motionEvent.rawY
                    startY = motionEvent.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    val a = abs(coordinator.first - view!!.x)
                    val b = abs(coordinator.second - view.y)
                    val c = sqrt(a.pow(2) + b.pow(2))

                    if (startY + 200f < motionEvent.rawY) {
                            setBgAlpha(if (1f-(c/500f) < 0f) 0f else 1f-(c/500f))
                            view.animate()
                                .x(motionEvent.rawX + moveX)
                                .y(motionEvent.rawY + moveY)
                                .setDuration(0)
                                .start()
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if ( dataBinding.viewBg.alpha == 0f ) {
                        supportFinishAfterTransition()
                    } else {
                        if (!isShowingInfo) {
                            setBgAlpha(1f)
                            view.animate()
                                .x(coordinator.first)
                                .y(coordinator.second)
                                .setDuration(100)
                                .start()
                        }
                    }
                }
            }
            true
        }

    }

    private fun setBgAlpha(alpha: Float) {
        dataBinding.viewBg.alpha = alpha
        dataBinding.tvDateTime.alpha = alpha
        dataBinding.tvSiteName.alpha = alpha
    }
}