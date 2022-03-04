package com.zheng.lib.utils

import android.content.Context
import android.view.View
import timber.log.Timber
import java.lang.reflect.Method

/**
 * @Author: Drelovey
 * @CreateDate: 2021/1/11 9:50
 */
object ReflectionUtils {

    /**
     * 根据方法名获取method
     */
    fun resolveMethod(context: Context, method: String): Method? {
        try {
            //上下文是否受限制
            if (!context.isRestricted) {
                return context.javaClass.getMethod(method, View::class.java)
            }
        } catch (e: NoSuchMethodException) {
            Timber.e("not find %s method", method)
        }
        return null
    }
}