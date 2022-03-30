package com.zheng.base.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.os.Process

/**
 * @Author: Drelovey
 * @CreateDate: 2020/4/28 16:18
 */
@SuppressLint("StaticFieldLeak")
object BaseUtils {

    var context: Context? = null

    //var netConfig: NetConfig? = null

    //赋值Lib context
    @JvmStatic
    fun init(context: Context) {
        BaseUtils.context = context.applicationContext
    }

    //判断当前进程是否是应用的主进程
    @SuppressLint("NewApi")
    @JvmStatic
    fun isMainProcess(context: Context): Boolean {
        val pid = Process.myPid()
        //Timber.d("isMainProcess->%d", pid)
        val activityManager = context.applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (appProcess in activityManager.runningAppProcesses) {
            if (appProcess.pid == pid) {
                return context.applicationContext.applicationInfo.packageName == appProcess.processName
            }
        }
        return false
    }
}