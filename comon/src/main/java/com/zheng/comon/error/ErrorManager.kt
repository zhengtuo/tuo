package com.zheng.comon.error

import com.zheng.comon.R
import com.zheng.comon.error.listener.ErrorFactory
import com.zheng.comon.utils.CommonUtils

/**
 * Created by Drelovey on 4/25/2021
 */

object ErrorManager : ErrorFactory {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorsMap.getValue(errorCode))
    }

    override fun getErrorString(errorId: Int): String {
        return CommonUtils.getStringById(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = hashMapOf(
            Pair(Error.NO_INTERNET_CONNECTION, getErrorString(R.string.error_no_internet)),
            Pair(Error.UN_KNOW, getErrorString(R.string.error_un_know)),
            Pair(Error.EMPTY, getErrorString(R.string.error_empty)),
        )

}