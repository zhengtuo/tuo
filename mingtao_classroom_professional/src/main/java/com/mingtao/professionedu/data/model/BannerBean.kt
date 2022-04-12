package com.mingtao.professionedu.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BannerBean {
    var bannerId: Int = 0
    var bannerName: String = ""
    var bannerThumb: String = ""
}