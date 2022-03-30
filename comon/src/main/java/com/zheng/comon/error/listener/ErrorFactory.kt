package com.zheng.comon.error.listener

import com.zheng.comon.error.Error


interface ErrorFactory {
    fun getError(errorCode: Int): Error
    fun getErrorString(errorId: Int): String
    val errorsMap: Map<Int, String>
}