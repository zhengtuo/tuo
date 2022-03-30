package com.zheng.base.data.errors

import com.zheng.lib.data.error.Error


interface ErrorFactory {
    fun getError(errorCode: Int): Error
}