package com.mingtao.professionedu.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class StudyInfoBean {
    /**
     * examinationAll : 4
     * examinationTodey : 0
     * playTimeTodey : 0
     * playTimeAll : 2
     */

    var examinationAll: Int = 0
    var playTimeToday = 0
    var examinationToday = 0
    var playTimeAll = 0
}