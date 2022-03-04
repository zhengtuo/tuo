package com.zheng.lib.binding.viewadapter.view

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.zheng.lib.base.viewmodel.BaseViewModel
import com.zheng.lib.binding.listener.BindingCommand
import com.zheng.lib.utils.launch
import kotlinx.coroutines.*


/**
 * @Author: Drelovey
 * @CreateDate: 2020/5/14 14:52
 */
object ViewAdapter {

    //防重复点击间隔(秒)
    private const val CLICK_INTERVAL = 1500L

    @SuppressLint("CheckResult")
    @JvmStatic
    @BindingAdapter(
        value = ["onClickCommand", "viewModel", "isDelayed", "interval"], requireAll = false
    )
    //防止重复点击
    fun onClickCommand(
        view: View, clickCommand: BindingCommand<*>?, viewModel: BaseViewModel?, //作用域 反正内存泄露
        isDelayed: Boolean, //是否启用
        interval: Long //延时时间
    ) {
        viewModel?.launch({
            var canClick = true
            view.setOnClickListener {
                if (canClick) {
                    canClick = false
                    launch({
                        delay(if (interval > 0L) interval else CLICK_INTERVAL)
                        canClick = true
                    })
                    val tag = view.tag
                    if (tag != null) {
                        clickCommand?.setData(tag)
                        clickCommand?.click()
                    }
                } else if (!isDelayed) {
                    clickCommand?.click()
                }
            }
        })
    }

    /**
     * view的显示隐藏
     */
    @JvmStatic
    @BindingAdapter(value = ["isVisible"], requireAll = false)
    fun isVisible(view: View, visibility: Boolean?) {
        if (visibility != null && visibility) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["marginTop"])
    fun setMargin(view: View?, margin: Int) {
        if (view != null) {
            try {
                val layoutParams = view.layoutParams as ConstraintLayout.LayoutParams
                //layoutParams.topMargin = ScreenUtils.dip2px(margin)
                view.layoutParams = layoutParams
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("invisible")
    fun visibleInvisible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }


    @JvmStatic
    @BindingAdapter(value = ["set_drawable"])
    fun setDrawableDsl(view: View, drawable: Drawable?) {
        drawable.apply {
            view.background = drawable
        }
    }

    @Suppress("ObjectLiteralToLambda")
    @JvmStatic
    @BindingAdapter(value = ["method", "viewModel"], requireAll = true)
    fun xmlClick(view: View?, method: (view: View) -> Unit = {}, viewModel: BaseViewModel) {
        view?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                try {
                    //利用反射通过方法名获取method,再使用invoke进行方法的调用

                    viewModel.launch({
                        method.invoke(view)
                    })
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }



}