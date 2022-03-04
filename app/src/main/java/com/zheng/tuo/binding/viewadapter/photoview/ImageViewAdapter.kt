@file:Suppress("unused")

package com.zheng.tuo.binding.viewadapter.photoview

import androidx.databinding.BindingAdapter
import com.bm.library.PhotoView

/**
 * @Author: Drelovey
 * @CreateDate: 2022/2/24
 */
object ImageViewAdapter {

    @JvmStatic
    @BindingAdapter(value = ["photoview_enable"])
    fun setPhotoViewEnable(photoView: PhotoView, enable: Boolean) {
        if (enable) {
            photoView.enable()
        } else {
            photoView.disenable()
        }
    }
}