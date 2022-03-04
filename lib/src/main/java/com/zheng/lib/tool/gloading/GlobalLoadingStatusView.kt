package com.zheng.lib.tool.gloading

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.zheng.lib.R
import com.zheng.lib.databinding.LayoutGlobalLoadingStatusBinding
import com.zheng.lib.tool.gloading.Gloading.*


/**
 * @Author: Drelovey
 * @CreateDate: 2020/5/26 11:35
 */
@SuppressLint("ViewConstructor")
class GlobalLoadingStatusView(context: Context, retryTask: Runnable) : LinearLayout(context) {
    var mBinding: LayoutGlobalLoadingStatusBinding? = null

    private var mRetryTask: Runnable? = retryTask

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.layout_global_loading_status,
                this,
                true
        )
    }

    fun setStatus(status: Int) {
        mBinding?.show = false
        when (status) {
            //加载中
            STATUS_LOADING -> mBinding?.show = true
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
        mBinding?.unbind()
    }

}