package com.mingtao.professionedu.ui.compose.main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jeremyliao.liveeventbus.LiveEventBus
import com.mingtao.professionedu.data.model.BannerBean
import com.mingtao.professionedu.data.model.HomeArticleBean
import com.mingtao.professionedu.data.model.HomeFloorModuleBean
import com.mingtao.professionedu.data.model.HomeTypeBean
import com.mingtao.professionedu.ui.compose.main.model.MTPCHomeModel
import com.zheng.base.data.model.Resource
import com.zheng.base.utils.launch
import com.zheng.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST", "EXPERIMENTAL_API_USAGE")
@HiltViewModel
class MTPCHomeVM @Inject constructor(private val mModel: MTPCHomeModel) : BaseViewModel() {

    //未读消息数量
    var messageSize by mutableStateOf(0)

    //热门搜索
    var hotSearchs = mutableStateOf(listOf<String>())

    //首页图片
    var bannerLists = mutableStateOf(listOf<BannerBean>())

    //首页分类
    var homeTypes = mutableStateOf(listOf<HomeTypeBean>())

    //首页资讯
    var articles = mutableStateOf(listOf<HomeArticleBean>())

    //首页课程推荐
    var recommends = mutableStateOf(listOf<HomeFloorModuleBean>())

    init {
        getData()
    }

    @ExperimentalCoroutinesApi
    fun getData() {
        launch({
            LiveEventBus.get("handleData").post(Resource.Loading<Any>())
            withContext(Dispatchers.IO) {
                val datas = ArrayList<Deferred<*>>()
                datas.add(async {
                    mModel.getHotSearchKeyword()
                })
                datas.add(async {
                    mModel.getBannerList("app_home")
                })
                datas.add(async {
                    mModel.getHomeType()
                })
                datas.add(async {
                    mModel.getArticleList(1, 2)
                })
                datas.add(async {
                    mModel.getHomeFloorModule(1, 12)
                })
                datas.forEach { it.await() }
                withContext(Dispatchers.Main) {
                    datas.forEach {
                        if (it.isCompleted) {
                            val resource = it.getCompleted() as Resource<Any>
                            if (resource is Resource.Success) {
                                when (resource.methodName) {
                                    "getHotSearchKeyword" -> hotSearchs.value = resource.data as List<String>

                                    "getBannerList" -> bannerLists.value = resource.data as List<BannerBean>

                                    "getHomeType" -> homeTypes.value = resource.data as List<HomeTypeBean>

                                    "getArticleList" -> articles.value = resource.data as List<HomeArticleBean>

                                    "getHomeFloorModule"-> recommends.value = resource.data as List<HomeFloorModuleBean>
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