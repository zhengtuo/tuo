package com.mingtao.professionedu.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CourseBean {

    /**
     * courseId : 810
     * courseNo : 0
     * courseName : 神奇叔叔青少儿精品体能课(不含运动装备)
     * courseTitle : 神奇叔叔青少儿精品体能课(不含运动装备)
     * keywords : 少儿体能精品课(无礼包版)
     * courseImage : https://shangke-img.oss-cn-beijing.aliyuncs.com/storage/app/video/202005081059164521.jpg
     * topCategoryId : 18
     * categoryId : 12
     * isTop : 1
     * labelId : 23
     * brandId : 1
     * clickCount : 1
     * sortOrder : 20
     * shopPrice : 199.0
     * marketPrice : 4988.0
     * promotePrice : 199.0
     * thumb : https://shangke-img.oss-cn-beijing.aliyuncs.com/storage/app/video/202005081326493247.jpg
     * isShow : 1
     * isHot : 1
     * isBest : 1
     * isNew : 1
     * isRecommend : 1
     * isFree : 0
     * isPromote : 0
     * status : 0
     * createTime : 2020-05-06T15:55:08.000+0000
     * updateTime : 2020-06-30T14:23:27.000+0000
     * teacherId : 55
     * classCount : 12
     * hotName : 少儿体能精品课
     * catLength : 17210
     * courseUseYears : 2
     * payCount : 3000
     * picCount : 500
     * isChuan : 0
     * isAudition : 0
     * isAgentTalk : 0
     * isAgentTopic : 0
     * courseDesc : <p>少儿体能精品课(无礼包版)</p>
     * isPay : null
     * progress : 0.0
     * currentCatalogId : null
     * currentLearnGoodsId : null
     * learnCount : null
     * topCategoryEntity : {"courseCategoryId":18,"parentId":0,"categoryName":"职业技能","status":0,"sortOrder":20,"isShow":1,"platform":"all","remark":"多一项技能就多一份就业机会","createTime":"2020-05-06T15:55:08.000+0000","updateTime":"2020-06-30T14:23:27.000+0000","categoryThumb":null,"childCourseCategoryList":null,"courseList":null}
     * categoryEntity : null
     */


    var courseName: String = ""
    var thumb: String = ""

    var isFree = 0

    var isPromote = 0
    var promoteTime: String? = ""
    var shopPrice = 0.0
    var promotePrice = 0.0
    var marketPrice = 0.0
//
    var payCount = 0
    var picCount = 0
}