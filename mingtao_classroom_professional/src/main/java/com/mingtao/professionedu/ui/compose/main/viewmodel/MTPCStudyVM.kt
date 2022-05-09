package com.mingtao.professionedu.ui.compose.main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jeremyliao.liveeventbus.LiveEventBus
import com.mingtao.professionedu.data.model.StudyInfoBean
import com.mingtao.professionedu.data.model.UserCourseBean
import com.mingtao.professionedu.data.model.VideoInfoBean
import com.mingtao.professionedu.ui.compose.main.model.MTPCStudyModel
import com.mingtao.professionedu.utils.MTPUtils
import com.zheng.base.data.model.Resource
import com.zheng.base.utils.launch
import com.zheng.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST", "EXPERIMENTAL_API_USAGE")
@HiltViewModel
class MTPCStudyVM @Inject constructor(private val mModel: MTPCStudyModel) : BaseViewModel() {

    //是否登录
    var isLogin by mutableStateOf(false)

    //登录信息
    var studyInfoBean by mutableStateOf(StudyInfoBean())

    //当前分类index
    var categoryIndex by mutableStateOf(0)

    //课程选中index
    var courseIndex by mutableStateOf(0)

    var nowTime: Long = 0

    //已购买课程
    var userCourses by mutableStateOf(listOf<UserCourseBean>())

    //已购买视频
    var userVideos by mutableStateOf(listOf<VideoInfoBean>())

    init {
        isLogin = MTPUtils.isLogin()
        if (isLogin) {
            getData()
        }
    }

    @ExperimentalCoroutinesApi
    fun getData() {
        launch({
            LiveEventBus.get("handleData").post(Resource.Loading<Any>())
            withContext(Dispatchers.IO) {
                val datas = ArrayList<Deferred<*>>()
                if (MTPUtils.isLogin()) {
                    datas.add(async {
                        mModel.getUserStudyInfo()
                    })
                }
                datas.add(async {
                    mModel.getUserCourse(1, 1)
                })

                datas.add(async {
                    mModel.getUserVideo(1, 10)
                })

                datas.forEach { it.await() }
                withContext(Dispatchers.Main) {
                    datas.forEach {
                        if (it.isCompleted) {
                            val resource = it.getCompleted() as Resource<Any>
                            if (resource is Resource.Success) {
                                when (resource.methodName) {
                                    "getUserStudyInfo" -> studyInfoBean = resource.data as StudyInfoBean

                                    "getUserCourse" -> {
                                        nowTime = resource.timestamp
                                        userCourses = resource.data as List<UserCourseBean>
                                    }

                                    "getUserVideo" -> userVideos = resource.data as List<VideoInfoBean>

                                }
                            } else {
                                LiveEventBus.get("handleData").post(resource)
                            }

                        }
                    }
                    LiveEventBus.get("handleData").post(Resource.Complete<Any>())
                }
            }

        }, {

        })
    }
}