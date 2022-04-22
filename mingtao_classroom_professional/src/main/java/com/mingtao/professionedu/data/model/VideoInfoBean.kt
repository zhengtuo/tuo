package com.mingtao.professionedu.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class VideoInfoBean {
    //图片地址
    var goodsThumb: String = ""

    //视频名称
    var goodsName: String = ""

    //是否免费
    var isFree = 0

    //销售价格
    var shopPrice = 0.0

    //原价
    var marketPrice = 0.0

    //点击量
    var clickCount = 0

    //简介
    var keywords: String = ""

    //视频总时长
    var catLength: Int? = 0

    @Json(name = "courseGoodsLearnLogEntity")
    var videoLearnRecordBean: VideoLearnRecordBean? = null
}