package com.mingtao.professionedu.lib.gloading

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.mingtao.professionedu.R
import com.mingtao.professionedu.databinding.LayoutGlobalLoadingStatusBinding
import com.mingtao.professionedu.lib.gloading.Gloading.*


/**
 * @Author: Drelovey
 * @CreateDate: 2020/5/26 11:35
 */
@SuppressLint("ViewConstructor")
class GlobalLoadingStatusView(context: Context, retryTask: Runnable) : LinearLayout(context) {
    var mBinding: LayoutGlobalLoadingStatusBinding? = null

    private var mRetryTask: Runnable? = retryTask
    private var animationDrawable: AnimationDrawable

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_global_loading_status, this, true)
        isClickable = true
        animationDrawable = mBinding?.image?.drawable as AnimationDrawable
    }

    fun setStatus(status: Int) {
        mBinding?.show = false
        isClickable = false
        when (status) {
            //加载中
            STATUS_LOADING -> {
                mBinding?.apply {
                    show = true
                    text.text = "加载中"
                    isClickable = true
                    image.post {
                        animationDrawable.start()
                    }
                }
            }

            //加载成功
            STATUS_LOAD_SUCCESS -> {

            }
            //加载失败
            STATUS_LOAD_FAILED -> {
            }
            //没有数据
            STATUS_EMPTY_DATA -> {
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animationDrawable.stop()
        mBinding?.unbind()

    }

}