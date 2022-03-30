@file:Suppress("unused")

package com.zheng.comon.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesUtils {

    private const val SP_NAME = "zheng_tuo"
    private var sharedPreferences: SharedPreferences? = null

    //判断sharedPreferences是否存在，不存在就创建
    private fun existOrCreate() {
        if (sharedPreferences == null) {
            sharedPreferences = CommonUtils.context!!.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
    }

    //保存字符串
    fun saveString(key: String, value: String) {
        existOrCreate()
        val isSuccess = sharedPreferences!!.edit().putString(key, value)
        isSuccess.apply()
    }

    //获取字符串
    fun getString(key: String, defaultValue: String): String {
        existOrCreate()
        return sharedPreferences!!.getString(key, defaultValue) ?: ""
    }

    //保存布尔
    fun saveBoolean(key: String, value: Boolean) {
        existOrCreate()
        val edit = sharedPreferences!!.edit().putBoolean(key, value)
        edit.apply()
    }

    //获取布尔
    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        existOrCreate()
        return sharedPreferences!!.getBoolean(key, defaultValue)
    }

    fun saveInt(key: String, value: Int) {
        existOrCreate()
        val edit = sharedPreferences!!.edit().putInt(key, value)
        edit.apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        existOrCreate()
        return sharedPreferences!!.getInt(key, defaultValue)
    }

    fun contains(key: String): Boolean {
        existOrCreate()
        return sharedPreferences!!.contains(key)
    }


}