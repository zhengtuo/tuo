package com.mingtao.professionedu.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LoginBean {
    var user_id: String = ""

    @Json(name = "Token")
    var token: String = ""

    var campus_id: String = ""
}