package com.zheng.lib.binding.viewadapter.toolbar

import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.gyf.immersionbar.ImmersionBar
import com.zheng.lib.utils.LibUtils

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/21 16:41
 */
object ToolbarAdapter {
    //设置左边返回图标
    @JvmStatic
    @BindingAdapter("icon")
    fun setIcon(toolbar: Toolbar, @DrawableRes icon: Int?) {
        icon?.let { toolbar.setNavigationIcon(it) }
    }

    //点击返回图标
    @JvmStatic
    @BindingAdapter("onNavigationClick")
    fun onNavigationClick(
        toolbar: Toolbar,
        listener: View.OnClickListener?
    ) {
        toolbar.setNavigationOnClickListener(listener)
    }

    //状态栏字体深色或亮色
    @JvmStatic
    @BindingAdapter("statusBarDarkFont")
    fun setStatusBarDarkFont(view: View, isDark: Boolean) {
        val activity = LibUtils.getActivityFromView(view)
        activity?.let {
            ImmersionBar.with(activity)
                .statusBarDarkFont(isDark)
                .init()
        }
    }

}