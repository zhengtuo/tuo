@file:Suppress("unused")

package com.zheng.comon.binding.viewadapter.imageview

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zheng.comon.utils.CommonUtils

/**
 * @Author: Drelovey
 * @CreateDate: 2020/8/5 10:50
 */
object ImageViewAdapter {

    @JvmStatic
    @BindingAdapter(value = ["src_res"])
    fun setImageBySrc(imageView: ImageView, @DrawableRes srcRes: Int) {
        imageView.setImageResource(srcRes)
    }

    @JvmStatic
    @BindingAdapter(value = ["url", "placeholder", "error", "radius", "circle"], requireAll = false)
    fun setImageByUrl(
        imageView: ImageView, url: Any?, @DrawableRes placeholder: Int, @DrawableRes error: Int, radius: Int, circle: Boolean
    ) {
        if (url == null) {
            return
        }
        var requestOptions = RequestOptions()
        // if Placeholder map is not empty set it
        if (placeholder != 0) {
            requestOptions = requestOptions.placeholder(placeholder)
        }
        // if Error graph is not empty set it
        if (error != 0) {
            requestOptions = requestOptions.placeholder(error)
        }
        // if radius is not empty set angle
        if (radius > 0) {
            requestOptions = requestOptions.transform(RoundedCorners(CommonUtils.dip2px(radius)))
        } else if (circle) {
            requestOptions = requestOptions.transform(CircleCrop())
        }
        val manager = Glide.with(imageView.context)
        val requestBuilder: RequestBuilder<Drawable?>
        if (url is Int) {
            requestBuilder = manager.load(url)
            requestOptions = requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE)
        } else {
            requestBuilder = manager.load(url)
        }
        requestBuilder.apply(requestOptions).into(imageView)
    }
}