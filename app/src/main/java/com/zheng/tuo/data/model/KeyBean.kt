package com.zheng.tuo.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class KeyBean {
    var body: String? = null
}