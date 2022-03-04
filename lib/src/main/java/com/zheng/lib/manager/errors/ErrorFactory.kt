package com.zheng.lib.manager.errors

import com.zheng.lib.data.error.Error


interface ErrorFactory {
    fun getError(errorCode: Int): Error
}