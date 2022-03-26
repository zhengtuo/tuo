package com.zheng.comon.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


object MD5Utils {

    //MD5 加密算法
    fun md5(content: String): String {
        try {
            //获取md5加密对象
            val instance: MessageDigest = MessageDigest.getInstance("MD5")
            //对字符串加密，返回字节数组
            val digest: ByteArray = instance.digest(content.toByteArray())
            val contentMd5 = StringBuffer()
            for (b in digest) {
                //获取低八位有效值 将整数转化为16进制
                val hexString = Integer.toHexString(0xFF and b.toInt())
                if (hexString.length == 1) {
                    //如果是一位的话，补0
                    contentMd5.append("0")
                }
                contentMd5.append(hexString)
            }
            return contentMd5.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

}