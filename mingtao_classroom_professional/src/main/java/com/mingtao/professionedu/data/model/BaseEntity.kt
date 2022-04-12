package com.mingtao.professionedu.data.model

import com.squareup.moshi.JsonClass

/**
 *
 * @Author: Drelovey
 * @CreateDate: 2020/1/22 16:31
 */
//@JsonClass moshi必备
@JsonClass(generateAdapter = true)
data class BaseEntity<T>(
    var code: Int = 0, var data: T? = null, var status: Boolean = false, var msg: String = ""
)