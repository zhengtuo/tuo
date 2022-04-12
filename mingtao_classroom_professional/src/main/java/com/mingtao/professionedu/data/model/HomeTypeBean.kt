package com.mingtao.professionedu.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class HomeTypeBean {

    /**
     * homePageOperationCategoryId : 2
     * homePageOperationCategoryName : 导航二
     * jumpType : 4
     * status : 1
     * sortOrder : 1
     * isShow : 1
     * platform : app
     * remark : 22
     * createTime : 2020-08-01T09:33:13.000+0000
     * updateTime : 2020-08-03T13:49:18.000+0000
     * thumb : https://shangke-img.oss-cn-beijing.aliyuncs.com/storage/data/article/202008011733067460.png
     * topCategoryId : 17
     * courseId : 0
     * sourceFrom : 3
     * courseEntity : null
     * categoryId : 0
     * linkUrl :
     */


    var homePageOperationCategoryId: Int = 0
    var homePageOperationCategoryName: String = ""
    var thumb: String = ""
}