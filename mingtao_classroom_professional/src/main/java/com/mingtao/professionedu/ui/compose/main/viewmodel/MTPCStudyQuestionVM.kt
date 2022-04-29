package com.mingtao.professionedu.ui.compose.main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jeremyliao.liveeventbus.LiveEventBus
import com.mingtao.professionedu.R
import com.mingtao.professionedu.data.model.CourseBean
import com.mingtao.professionedu.data.model.StudyQuestionType
import com.mingtao.professionedu.ui.compose.main.model.MTPCStudyModel
import com.zheng.base.data.model.Resource
import com.zheng.base.utils.launch
import com.zheng.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST", "EXPERIMENTAL_API_USAGE")
@HiltViewModel
class MTPCStudyQuestionVM @Inject constructor(private val mModel: MTPCStudyModel) : BaseViewModel() {

    //题库选中index
    var questionIndex by mutableStateOf(0)
    //是否正在刷新
    var refreshing by mutableStateOf(false)

    //已购买有题库的课程
    var userQuestions by mutableStateOf(listOf<CourseBean>())

    var studyQuestionTypes by mutableStateOf(listOf(
        StudyQuestionType("历年真题", R.mipmap.mtp_question_real, true, mutableListOf()),
        StudyQuestionType("模拟演练", R.mipmap.mtp_question_imitate, true,mutableListOf()),
        StudyQuestionType("密题卷", R.mipmap.mtp_question_secret, true,mutableListOf()),
        StudyQuestionType("章节测试", R.mipmap.mtp_question_chapter, true,mutableListOf()),
    ))

    init {
        getData()
    }

    @ExperimentalCoroutinesApi
    fun getData() {
        launch({
            refreshing = true
            withContext(Dispatchers.IO) {
                val datas = ArrayList<Deferred<*>>()

                datas.add(async {
                    mModel.getUserQuestion()
                })

                datas.forEach { it.await() }
                withContext(Dispatchers.Main) {
                    datas.forEach {
                        if (it.isCompleted) {
                            val resource = it.getCompleted() as Resource<Any>
                            if (resource is Resource.Success) {
                                when (resource.methodName) {
                                    "getUserQuestion" -> {
                                        userQuestions = resource.data as List<CourseBean>
                                    }
                                }
                            } else {
                                LiveEventBus.get("handleData").post(resource)
                            }
                        }
                    }
                    refreshing = false
                }
            }
        }, {

        })
    }
}