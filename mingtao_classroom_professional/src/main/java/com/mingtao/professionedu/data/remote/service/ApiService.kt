package com.mingtao.professionedu.data.remote.service

import com.mingtao.professionedu.data.model.BaseEntity
import com.mingtao.professionedu.data.model.LoginBean
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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


}