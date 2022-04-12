package com.mingtao.professionedu.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class HomeArticleBean {

    var categoryId: Int = 0
    var categoryName: String = ""

    @Json(name = "childArticleCategoryList")
    var items: List<HomeArticleItem>? = null

    var title: String = ""

    var updateTime: String = ""
    var createTime: String = ""
}