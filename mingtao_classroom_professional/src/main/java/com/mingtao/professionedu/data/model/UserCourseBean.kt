package com.mingtao.professionedu.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserCourseBean {
    @Json(name = "courseEntity")
    var courseBean: CourseBean? = null

    var endTime: String? = null
    var isRefund = 0

    var learnCount: Int = 0

    @Json(name = "lastLearnLogEntity")
    var learnRecordBean: CourseLearnRecordBean? = null
}