package com.mingtao.professionedu.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TopicBean {
    var topTypeId = 0
    var examinationName: String? = ""

    @Json(name = "customerExaminationTestLogEntity")
    var testRecordBean: TestRecordBean? = null

    var endTime: String? = ""

    var questionCount = 0

}