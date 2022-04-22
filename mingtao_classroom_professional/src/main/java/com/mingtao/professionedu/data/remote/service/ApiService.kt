package com.mingtao.professionedu.data.remote.service

import com.mingtao.professionedu.data.model.*
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/21 11:12
 */
interface ApiService {

    /**
     * 发送短信验证码
     */
    @POST("/smsLog/sendSms")
    @FormUrlEncoded
    suspend fun sendSms(@Field("phone") phone: String, @Field("codeType") codeType: Int): ApiResponse<BaseEntity<Any>>

    //验证码登录
    @POST("/customer/login")
    @FormUrlEncoded
    suspend fun codeLogin(@Field("loginName") loginName: String, @Field("code") code: String, @Field("platform") platform: String, @Field("loginType") loginType: Int): ApiResponse<BaseEntity<LoginBean>>

    //密码登录
    @POST("/customer/login")
    @FormUrlEncoded
    suspend fun passwordLogin(@Field("loginName") loginName: String, @Field("password") password: String, @Field("platform") platform: String, @Field("loginType") loginType: Int): ApiResponse<BaseEntity<LoginBean>>

    //修改密码
    @POST("/customer/editPassword")
    @FormUrlEncoded
    suspend fun changePassword(@Field("loginName") loginName: String, @Field("password") password: String, @Field("code") code: String, @Field("codeType") codeType: Int): ApiResponse<BaseEntity<Any>>

    //热门搜索词
    @GET("/public/search")
    suspend fun getHotSearchKeyword(): ApiResponse<BaseEntity<List<String>>>

    //轮播图列表  position 轮播图显示位置，app_home-首页,app_live-直播页，app_courseList-课程列表页，app_newsList-新闻列表页，app_examinationList-题库页面，app_datumList-资料列表页
    @GET("/banner/findList?platform=app")
    suspend fun getBannerList(@Query("position") position: String): ApiResponse<BaseEntity<List<BannerBean>>>

    //首页类目
    @GET("/homePageOperationCategory/findList?platform=app")
    suspend fun getHomeType(): ApiResponse<BaseEntity<List<HomeTypeBean>>>

    //首页资讯列表
    @GET("/article/findList?platform=app")
    suspend fun getArticleList(@Query("page") page: Int, @Query("limit") pageSize: Int): ApiResponse<BaseEntity<List<HomeArticleBean>>>

    //首页课程楼层
    @GET("/floorModule/findList?locationType=1")
    suspend fun getHomeFloorModule(@Query("page") page: Int, @Query("limit") pageSize: Int): ApiResponse<BaseEntity<List<HomeFloorModuleBean>>>

    //首页讲师列表
    @GET("/teacher/findList")
    suspend fun getTeacherList(@Query("page") page: Int, @Query("limit") PageSize: Int, @Query("isStar") isStar: Int): ApiResponse<BaseEntity<List<TeacherBean>>>

    //首页猜你喜欢列表
    @GET("/courseGoods/findList?like=1")
    suspend fun getGuessYouLikeList(@Query("page") page: Int, @Query("limit") PageSize: Int): ApiResponse<BaseEntity<List<VideoInfoBean>>>

    //未读消息数量
    @GET("/pushLog/findUnreadCount")
    suspend fun getUnreadMessageCount(): ApiResponse<BaseEntity<Int>>


    //用户学习信息
    @GET("/customer/studyInfo")
    suspend fun getUserStudyInfo(): ApiResponse<BaseEntity<StudyInfoBean>>

    //获取用户购买课程
    @GET("/customerCourse/findList")
    suspend fun getUserCourse(@Query("withCatalog") withCatalog: Int, @Query("withLearnInfo") withLearnInfo: Int): ApiResponse<BaseEntity<List<UserCourseBean>>>

    //获取用户购买视频
    @GET("/customerCourseGoods/findList?withLearnInfo=1")
    suspend fun getUserVideo(@Query("page") page: Int, @Query("limit") pageSize: Int): ApiResponse<BaseEntity<List<VideoInfoBean>>>

    //获取用户拥有题库的课程
    @GET("/customerExamination/findExaminationCourse")
    suspend fun getUserBuyCourseQuestion(): ApiResponse<BaseEntity<List<CourseBean>>>

    //获取已购买课程试卷列表
    @GET("/customerExamination/findList")
    suspend fun getUserBuyCourseTopics(@Query("courseId") courseId:Int): ApiResponse<BaseEntity<List<TopicBean>>>
}