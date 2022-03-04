package com.zheng.lib.manager.errors

import com.zheng.lib.data.error.Error
import com.zheng.lib.data.error.ErrorMapper
import javax.inject.Inject

/**
 * Created by Drelovey on 4/25/2021
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorFactory {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }

}