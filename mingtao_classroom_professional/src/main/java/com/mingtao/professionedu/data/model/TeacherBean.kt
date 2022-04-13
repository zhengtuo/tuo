package com.mingtao.professionedu.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TeacherBean {
    var teacherId: Int = 0
    var teacherName = ""
    var expert = ""
    var teacherAvatar = ""
}