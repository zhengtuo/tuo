package com.zheng.comon.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {


    val mUtcFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    val mHMFormat = "HH:mm"


    //UTC时间格式转Date
    private fun utcToDate(utcTime: String): Date {
        val df = SimpleDateFormat(mUtcFormat, Locale.CHINA)
        df.timeZone = TimeZone.getTimeZone("UTC")
        val date: Date?
        val calendar = Calendar.getInstance()
        try {
            date = df.parse(utcTime)
            if (date != null) {
                calendar.time = date
            }
            //calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 8);
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return calendar.time
    }

    //判断时间戳是否为当天
    fun isTimeToday(time: Long): Boolean {
        val calendar1 = Calendar.getInstance()
        val calendar2 = Calendar.getInstance()
        calendar1.timeInMillis = time
        calendar2.time = Date()
        return calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
    }

    //格式化UTC日期为时分
    fun utcToHM(utcTime: String): String {

        //当地时间格式
        val localFormat = SimpleDateFormat(mHMFormat, Locale.CHINA)
        return localFormat.format(utcToDate(utcTime).time)
    }

    //UTC时间格式化 自定义格式
    fun utcToTime(utcTime: String, format: String): String {
        val localFormat = SimpleDateFormat(format, Locale.CHINA) //当地时间格式
        return localFormat.format(utcToDate(utcTime).time)
    }

    //UTC时间格式转时间戳
    fun utcToTimestamp(dataTime: String): Long {
        return utcToDate(dataTime).time
    }

    //60秒转  小时1分
    fun secondToHoursMinute(second: Int): String {
        if (second == 0) {
            return "0分"
        }
        if (second <= 60) {
            return "1分"
        }
        val minute: Int
        val hours: Int = second / 3600
        minute = (second - hours * 3600) / 60
        return if (hours == 0) {
            minute.toString() + "分"
        } else {
            hours.toString() + "小时" + minute + "分"
        }
    }

    //转换播放时间  秒转几分几秒
    fun changeVideoTime(time: Int): String {
        if (time == 0) {
            return ""
        }
        val minute: Int = time / 60
        val second: Int = time % 60
        return if (second == 0) {
            minute.toString() + "分"
        } else {
            minute.toString() + "分" + second + "秒"
        }
    }

    //练习时间   返回几天前或1月1日/19年12月31日
    fun getTestTime(utcTime: String): String? {
        val date: Date = utcToDate(utcTime)
        val calendar1 = Calendar.getInstance()
        val calendar2 = Calendar.getInstance()
        calendar1.time = date
        calendar2.time = Date()
        return if (calendar1[Calendar.YEAR] == calendar2[Calendar.YEAR]) {
            if (calendar1[Calendar.DAY_OF_YEAR] == calendar2[Calendar.DAY_OF_YEAR]) {
                utcToHM(utcTime)
            } else if ((calendar2.timeInMillis - calendar1.timeInMillis) / 1000 / 24 / 60 / 60 < 7) {
                if ((calendar2.timeInMillis - calendar1.timeInMillis) / 1000 / 24 / 60 / 60 <= 0) 1.toString() + "天前" else ((calendar2.timeInMillis - calendar1.timeInMillis) / 1000 / 24 / 60 / 60).toString() + "天前"
            } else {
                val localFormat = SimpleDateFormat("MM月dd日", Locale.CHINA)
                localFormat.format(date.time)
            }
        } else {
            val localFormat = SimpleDateFormat("yy年MM月dd日", Locale.CHINA)
            localFormat.format(date.time)
        }
    }
}