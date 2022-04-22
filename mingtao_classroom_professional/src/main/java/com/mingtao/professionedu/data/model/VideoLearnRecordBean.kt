package com.mingtao.professionedu.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class VideoLearnRecordBean {
    var playTime: Int? = 0
}