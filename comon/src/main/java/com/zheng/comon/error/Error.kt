package com.zheng.comon.error

/**
 * Created by AhmedEltaher on 5/12/2016
 */

@Suppress("unused")
open class Error(val code: Int, val description: String) {
    constructor(exception: Exception) : this(
        code = UN_KNOW, description = exception.message
            ?: ""
    )

    companion object {
        const val NO_INTERNET_CONNECTION = 1
        const val UN_KNOW = -1
        const val HAVE_MESSAGE = -2
        const val EMPTY = 404
    }
}