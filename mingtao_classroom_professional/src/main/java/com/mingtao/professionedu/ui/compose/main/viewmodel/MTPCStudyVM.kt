package com.mingtao.professionedu.ui.compose.main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jeremyliao.liveeventbus.LiveEventBus
import com.mingtao.professionedu.data.model.StudyInfoBean
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

    //未读消息数量
    var studyInfoBean by mutableStateOf(StudyInfoBean())


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

                datas.forEach { it.await() }
                withContext(Dispatchers.Main) {
                    datas.forEach {
                        if (it.isCompleted) {
                            val resource = it.getCompleted() as Resource<Any>
                            if (resource is Resource.Success) {
                                when (resource.methodName) {
                                    "getUserStudyInfo" -> studyInfoBean = resource.data as StudyInfoBean
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