package com.mingtao.professionedu.ui.compose.main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jeremyliao.liveeventbus.LiveEventBus
import com.mingtao.professionedu.R
import com.mingtao.professionedu.data.model.StudyQuestionType
import com.mingtao.professionedu.data.model.TopicBean
import com.mingtao.professionedu.ui.compose.main.model.MTPCStudyModel
import com.zheng.base.data.model.Resource
import com.zheng.base.utils.launch
import com.zheng.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Suppress("UNCHECKED_CAST", "EXPERIMENTAL_API_USAGE")
@HiltViewModel
class MTPCStudyQuestionItemVM @Inject constructor(private val mModel: MTPCStudyModel) : BaseViewModel() {

    //已购买课程题库列表
    var topicBeans by mutableStateOf(listOf<TopicBean>())

    var studyQuestionTypes by mutableStateOf(listOf(
        StudyQuestionType("历年真题", R.mipmap.mtp_question_real, true, mutableListOf()),
        StudyQuestionType("模拟演练", R.mipmap.mtp_question_imitate, true,mutableListOf()),
        StudyQuestionType("密题卷", R.mipmap.mtp_question_secret, true,mutableListOf()),
        StudyQuestionType("章节测试", R.mipmap.mtp_question_chapter, true,mutableListOf()),
    ))

    fun getUserBuyCourseTopics(courseId: Int) {
        launch({
                val result = withContext(Dispatchers.IO) { mModel.getUserBuyCourseTopics(courseId) }
                withContext(Dispatchers.Main) {
                    if (result is Resource.Success) {
                        topicBeans = result.data as List<TopicBean>
                        topicBeans.forEach {
                            when(it.topTypeId) {
                                1-> studyQuestionTypes[0].topicBeans.add(it)
                                2-> studyQuestionTypes[3].topicBeans.add(it)
                                3->studyQuestionTypes[1].topicBeans.add(it)
                                4->studyQuestionTypes[2].topicBeans.add(it)
                            }
                        }
                    } else {
                        LiveEventBus.get("handleData").post(result)
                    }
                }
        })
    }
}
