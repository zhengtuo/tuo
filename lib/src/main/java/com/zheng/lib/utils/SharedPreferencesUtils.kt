package com.zheng.lib.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesUtils {

    private const val SP_NAME = "zheng_tuo"
    var sharedPreferences: SharedPreferences? = null

    //判断sharedPreferences是否存在，不存在就创建
    fun existOrCreate() {
        if (sharedPreferences == null) {
            sharedPreferences = LibUtils.context!!.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
    }

    //保存字符串
    fun saveString(key: String, value: String) {
        existOrCreate()
        sharedPreferences!!.edit().putString(key, value).apply()
    }

    //获取字符串
    fun getString(key: String, defaultValue: String): String {
        existOrCreate()
        return sharedPreferences!!.getString(key, defaultValue) ?: ""
    }

    //保存布尔
    fun saveBoolean(key: String, value: Boolean) {
        existOrCreate()
        sharedPreferences!!.edit().putBoolean(key, value).apply()
    }

    //获取布尔
    fun getBoolean(key: String, defaultValue: Boolean) {
        existOrCreate()
        sharedPreferences!!.getBoolean(key, defaultValue)
    }

    fun saveInt(key: String, value: Int) {
        existOrCreate()
        sharedPreferences!!.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int) {
        existOrCreate()
        sharedPreferences!!.getInt(key, defaultValue)
    }

    fun contains(key: String): Boolean {
        existOrCreate()
        return sharedPreferences!!.contains(key)
    }


}