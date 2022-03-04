package com.zheng.tuo.tool.drawabledsl

import com.github.forjrking.drawable.Shape
import com.github.forjrking.drawable.resourceDrawable
import com.github.forjrking.drawable.selectorDrawable
import com.github.forjrking.drawable.shapeDrawable
import com.zheng.tuo.R

object DrawableDslManager {
    @JvmField
    val login_edit_bg = shapeDrawable {
        shape(Shape.RECTANGLE)
        solid("#33FFFFFF")
        corner(45F)
    }

    @JvmField
    val login_btn = shapeDrawable {
        shape(Shape.RECTANGLE)
        solid("#EFF0F0")
        corner(45F)
    }

    val login_cb_normal = resourceDrawable(R.mipmap.ic_delete_item_no_select)

    val login_cb_selected = resourceDrawable(R.mipmap.ic_delete_item_select)

    @JvmField
    val login_cb = selectorDrawable {
        checked = login_cb_selected
        normal = login_cb_normal
    }


}