package com.mingtao.professionedu.data.constants.drawabledsl

import com.github.forjrking.drawable.Shape
import com.github.forjrking.drawable.resourceDrawable
import com.github.forjrking.drawable.selectorDrawable
import com.github.forjrking.drawable.shapeDrawable
import com.mingtao.professionedu.R

object DrawableDslManager {


    @JvmField
    val login_text_normal = shapeDrawable {
        shape(Shape.RECTANGLE)
        solid("#47A3FF")
        corner(5F)
    }

    @JvmField
    val login_text_check = shapeDrawable {
        shape(Shape.RECTANGLE)
        solid("#F7F7F7")
        corner(5F)
    }

    val main_tab_home_normal = resourceDrawable(R.mipmap.mtp_home_normal)
    val main_tab_home_select = resourceDrawable(R.mipmap.mtp_home_select)

    @JvmField
    val tab_home = selectorDrawable {
        normal = main_tab_home_normal
        checked = main_tab_home_select
    }

    val main_tab_study_normal = resourceDrawable(R.mipmap.mtp_study_normal)
    val main_tab_study_select = resourceDrawable(R.mipmap.mtp_study_select)

    @JvmField
    val tab_study = selectorDrawable {
        normal = main_tab_study_normal
        checked = main_tab_study_select
    }

    val main_tab_find_normal = resourceDrawable(R.mipmap.mtp_news_normal)
    val main_tab_find_select = resourceDrawable(R.mipmap.mtp_news_select)

    @JvmField
    val tab_find = selectorDrawable {
        normal = main_tab_find_normal
        checked = main_tab_find_select
    }

    val main_tab_my_normal = resourceDrawable(R.mipmap.mtp_user_normal)
    val main_tab_my_select = resourceDrawable(R.mipmap.mtp_user_select)

    @JvmField
    val tab_my = selectorDrawable {
        normal = main_tab_my_normal
        checked = main_tab_my_select
    }
}