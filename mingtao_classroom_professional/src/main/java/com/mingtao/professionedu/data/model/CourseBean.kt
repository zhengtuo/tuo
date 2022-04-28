package com.mingtao.professionedu.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CourseBean {
    var courseId:Int = 0
    var courseName: String = ""
    var thumb: String = ""

    var isFree:Int? = 0

    var isPromote:Int? = 0
    var promoteTime: String? = ""
    var shopPrice:Double? = 0.0
    var promotePrice:Double? = 0.0
    var marketPrice:Double? = 0.0
//
    var payCount:Int? = 0
    var picCount:Int? = 0
    var classCount:Int? = 0

    var catalogList: List<CatalogBean>? = null
}