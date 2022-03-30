@file:Suppress("unused")

package com.zheng.comon.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Process
import android.provider.MediaStore
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.annotation.StringRes
import com.zheng.comon.constants.ComonConstants
import timber.log.Timber

/**
 * @Author: Drelovey
 * @CreateDate: 2020/4/28 16:18
 */
@SuppressLint("StaticFieldLeak")
object CommonUtils {

    var context: Context? = null

    //赋值Lib context
    @JvmStatic
    fun init(context: Context) {
        CommonUtils.context = context.applicationContext
    }

    //全局获取ApplicationContext
    fun getLibContext(): Context {
        if (context != null) return context as Context
        throw NullPointerException("should init first")
    }

    //判断当前进程是否是应用的主进程
    @SuppressLint("NewApi")
    @JvmStatic
    fun isMainProcess(context: Context): Boolean {
        val pid = Process.myPid()
        Timber.d("isMainProcess->%d", pid)
        val activityManager = context.applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (appProcess in activityManager.runningAppProcesses) {
            if (appProcess.pid == pid) {
                return context.applicationContext.applicationInfo.packageName == appProcess.processName
            }
        }
        return false
    }


    //Density（密度）屏幕上每平方英寸（2.54 ^ 2 平方厘米）中含有的像素点数量
    private fun getDensity(): Float {
        return context?.resources?.displayMetrics?.density ?: 0F
    }

    private fun getScaledDensity(): Float {
        return context?.resources?.displayMetrics?.scaledDensity ?: 0F
    }

    //dp转为px
    fun dip2px(dip: Int): Int {
        val density: Float = getDensity()
        return (dip * density + 0.5).toInt()
    }

    //sp转为px
    fun sp2px(sp: Int): Float {
        val scaledDensity: Float = getScaledDensity()
        return scaledDensity * sp
    }

    //全局获取String资源
    fun getStringById(@StringRes id: Int): String {
        return context?.resources?.getString(id) ?: "0"
    }


    //判断是否有网络
    @JvmStatic
    fun checkNet(): Boolean {
        if (context == null) return false
        return isWifiConnection(context!!) || isStationConnection(context!!)
    }

    //是否使用WIFI联网
    @Suppress("DEPRECATION")
    @SuppressLint("MissingPermission")
    fun isWifiConnection(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return if (networkInfo != null) {
            networkInfo.isAvailable && networkInfo.isConnected
        } else false
    }


    //是否使用基站联网
    @Suppress("DEPRECATION")
    @SuppressLint("MissingPermission")
    fun isStationConnection(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return if (networkInfo != null) {
            networkInfo.isAvailable && networkInfo.isConnected
        } else false
    }

    //根据view获取activity
    @JvmStatic
    fun getActivityFromView(view: View): Activity? {
        view.let {
            var context = it.context
            while (context is ContextWrapper) {
                if (context is Activity) {
                    return context
                }
                context = context.baseContext
            }
        }
        return null
    }

    //获取状态栏高度
    fun getStatusBarHeight(context: Context): Int {
        var result = 24
        val resId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        result = if (resId > 0) {
            context.resources.getDimensionPixelSize(resId)
        } else {
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, result.toFloat(), Resources.getSystem().displayMetrics).toInt()
        }
        return result
    }

    /**
     * 增加View上边距（MarginTop）一般是给高度为 WARP_CONTENT 的小控件用的
     */
    fun setMarginTop(context: Context, view: View) {
        if (Build.VERSION.SDK_INT >= ComonConstants.MIN_API) {
            val lp = view.layoutParams
            if (lp is MarginLayoutParams) {
                lp.topMargin += getStatusBarHeight(context) //增高
            }
            view.layoutParams = lp
        }
    }

    fun startActivity(activity: Activity, name: Class<*>) {
        val intent = Intent(activity, name)
        activity.startActivity(intent)
    }

    @SuppressLint("Range")
    @JvmStatic
    fun getImageForMedia(imageName: String): Uri? {
        val contentResolver = context!!.contentResolver
        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media.DISPLAY_NAME + "= '$imageName'", null, null)
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    val imgId = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID))
                    val uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imgId.toString())
                    return uri
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            cursor.close()
        }
        return null
    }

    //获取手机厂商
    fun getDeviceBrand(): String {
        return Build.BRAND
    }

    //获取手机型号
    fun getDeviceModel(): String {
        return Build.MODEL
    }

    //获取系统版本号
    fun getSystemVersion(): String {
        return Build.VERSION.RELEASE
    }

    //获取版本名
    fun getVersionName(): String {
        val packageInfo: PackageInfo = getPackageInfo(context!!) ?: return ""
        return packageInfo.versionName ?: ""
    }

    private fun getPackageInfo(context: Context): PackageInfo? {
        val pi: PackageInfo
        try {
            val pm = context.packageManager
            pi = pm.getPackageInfo(context.packageName, PackageManager.GET_CONFIGURATIONS)
            return pi
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return null
    }
}