package com.mingtao.professionedu.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class HomeFloorModuleBean {
    var floorModuleName: String = ""
    var floorModuleTitle: String = ""
    //0.更多 1.换一换
    var jumpType = 0
    //1.课程 2.视频
    var courseType = 0
    //课程
    var courseEntityList: List<CourseBean>? = null
    //视频
    var courseGoodsEntityList: List<VideoInfoBean>? = null
}