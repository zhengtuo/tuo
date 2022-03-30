package com.mingtao.professionedu.utils

import com.zheng.comon.utils.SharedPreferencesUtils

object MTPUtils {
    fun isLogin(): Boolean {
        return SharedPreferencesUtils.getInt("loginType", 0) == 1
    }
}