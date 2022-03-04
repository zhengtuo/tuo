package com.zheng.tuo.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * iconfont special view
 */
class IconFontView : AppCompatTextView {
    //构造方法
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        val icon = Typeface.createFromAsset(context.assets, "iconfont.ttf")
        this.typeface = icon
    }

}