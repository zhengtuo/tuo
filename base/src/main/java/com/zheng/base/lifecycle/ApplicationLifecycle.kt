package com.zheng.lib.base.lifecycle

import android.content.Context

/**
 * @Author: Drelovey
 * @CreateDate: 2020/4/28 12:07
 * 代理{@link Application} 的生命周期, 实现类见
 */
interface ApplicationLifecycle {
    /**
     * 在[attachBaseContext(Context)]Application. 中执行
     *
     * @param base
     */
    fun attachBaseContext(base: Context, isDebug: Boolean)

    /**
     * 在Application.onCreate 中执行
     */
    fun onCreate()

    /**
     * 在Application.onTerminate 中执行
     */
    fun onTerminate()

    /**
     * 在Application.onLowMemory 中执行
     */
    fun onLowMemory()

    /**
     * 在Application.onTrimMemory 中执行
     */
    fun onTrimMemory(level: Int)
}