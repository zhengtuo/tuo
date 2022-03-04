package com.zheng.lib.binding.viewadapter.textview

import android.graphics.Typeface
import android.os.Build
import android.text.Html
import android.text.TextUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/21 16:41
 */
object TextViewAdapter {

    @BindingAdapter("size")
    @JvmStatic
    fun setSize(textView: TextView, size: Float) {
        textView.textSize = size
    }

    @BindingAdapter("bold")
    @JvmStatic
    fun setBold(textView: TextView, bold: Boolean) {
        if (bold) {
            textView.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        } else {
            textView.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
        }
    }

    @BindingAdapter(value = ["html_text"])
    @JvmStatic
    fun htmlText(textView: TextView, content: String?) {
        if (!TextUtils.isEmpty(content)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.text = Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT)
            } else {
                textView.text = Html.fromHtml(content)
            }
        } else {
            textView.text = ""
        }
    }

}