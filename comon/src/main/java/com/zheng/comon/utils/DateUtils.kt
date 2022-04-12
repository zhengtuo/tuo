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
}