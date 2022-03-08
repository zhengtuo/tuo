package com.zheng.tuo.tool.drawabledsl

import com.github.forjrking.drawable.Shape
import com.github.forjrking.drawable.resourceDrawable
import com.github.forjrking.drawable.selectorDrawable
import com.github.forjrking.drawable.shapeDrawable
import com.zheng.tuo.R
import kotlinx.coroutines.selects.select

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
        normal = login_cb_normal
        checked = login_cb_selected
    }

    val main_tab_home_normal = resourceDrawable(R.mipmap.home_normal)
    val main_tab_home_select = resourceDrawable(R.mipmap.home_select)

    @JvmField
    val tab_home = selectorDrawable {
        normal = main_tab_home_normal
        checked = main_tab_home_select
    }

    val main_tab_course_normal = resourceDrawable(R.mipmap.course_normal)
    val main_tab_course_select = resourceDrawable(R.mipmap.course_select)

    @JvmField
    val tab_course = selectorDrawable {
        normal = main_tab_course_normal
        checked = main_tab_course_select
    }

    val main_tab_live_normal = resourceDrawable(R.mipmap.video_normal)
    val main_tab_live_select = resourceDrawable(R.mipmap.video_select)

    @JvmField
    val tab_live = selectorDrawable {
        normal = main_tab_live_normal
        checked = main_tab_live_select
    }

    val main_tab_study_normal = resourceDrawable(R.mipmap.study_circle_normal)
    val main_tab_study_select = resourceDrawable(R.mipmap.study_circle_select)

    @JvmField
    val tab_study = selectorDrawable {
        normal = main_tab_study_normal
        checked = main_tab_study_select
    }

    val main_tab_my_normal = resourceDrawable(R.mipmap.user_normal)
    val main_tab_my_select = resourceDrawable(R.mipmap.user_select)

    @JvmField
    val tab_my = selectorDrawable {
        normal = main_tab_my_normal
        checked = main_tab_my_select
    }

    @JvmField
    val tab_main_color = selectorDrawable {
        normal = resourceDrawable(R.color.color_3081e4)
        checked = resourceDrawable(R.color.color_696969)
    }
}