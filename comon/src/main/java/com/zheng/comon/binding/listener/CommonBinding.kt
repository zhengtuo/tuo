package com.zheng.comon.binding.listener

import android.view.View
import com.zheng.comon.utils.CommonUtils


object CommonBinding {
    //点击返回键
    @JvmField
    var backCommand: BindingClickT<View> = object : BindingClickT<View> {
        override fun click(t: View) {
            val activity = CommonUtils.getActivityFromView(t)
            activity?.onBackPressed()
        }
    }

    //关闭activity
    @JvmField
    var finishCommand: BindingClickT<View> = object : BindingClickT<View> {
        override fun click(t: View) {
            val activity = CommonUtils.getActivityFromView(t)
            activity?.finish()
        }
    }

    @JvmField
    var jumpClick: BindingClickT<String> = object : BindingClickT<String> {
        override fun click(t: String) {
            //ARouter.getInstance().build(t).navigation()
        }
    }

    @JvmField
    var jumpCommand = BindingCommand(jumpClick)
}