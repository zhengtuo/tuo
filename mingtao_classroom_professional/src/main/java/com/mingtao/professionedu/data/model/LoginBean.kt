package com.mingtao.professionedu.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LoginBean {
    var rememberToken: String = ""

    var loginName: String = ""

    var intentionCourseId: String? = ""
}