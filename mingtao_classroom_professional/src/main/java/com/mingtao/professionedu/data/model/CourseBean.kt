package com.mingtao.professionedu.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CourseBean {
    var courseName: String = ""
    var thumb: String = ""
}