package com.mingtao.professionedu.ui.compose.main.view

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.gyf.immersionbar.ImmersionBar
import com.mingtao.professionedu.R
import com.mingtao.professionedu.data.model.UserCourseBean
import com.mingtao.professionedu.data.model.VideoInfoBean
import com.mingtao.professionedu.data.model.VideoLearnRecordBean
import com.mingtao.professionedu.ui.compose.login.view.noClickable
import com.mingtao.professionedu.ui.compose.main.viewmodel.MTPCStudyQuestionItemVM
import com.mingtao.professionedu.ui.compose.main.viewmodel.MTPCStudyQuestionVM
import com.mingtao.professionedu.ui.compose.main.viewmodel.MTPCStudyVM
import com.mingtao.professionedu.ui.compose.theme.*
import com.zheng.base.utils.launch
import com.zheng.comon.utils.CommonUtils
import com.zheng.comon.utils.DateUtils

@ExperimentalPagerApi
@Composable
fun StudyPage(vm: MTPCStudyVM = viewModel()) {

    val statusBarHeight: Int = ImmersionBar.getStatusBarHeight(CommonUtils.context!!)
    //转换状态栏高度为dp
    val statusBarHeightDp = with(LocalDensity.current) {
        statusBarHeight.toDp()
    }

    val studyInfoBean = vm.studyInfoBean

    Box {
        Box(Modifier.fillMaxWidth().height(130.dp).background(Brush.linearGradient(colors = listOf(color_47A3FF, color_4DCDFD), start = Offset(0f, Float.POSITIVE_INFINITY), end = Offset(Float.POSITIVE_INFINITY, 0f)))) {

        }
        Column(Modifier.fillMaxWidth().padding(top = statusBarHeightDp + 13.dp)) {
            Text(text = "学习中心", Modifier.align(Alignment.CenterHorizontally), fontSize = 16.sp, color = Color.White)
            Spacer(Modifier.height(13.dp))
            val listState = rememberLazyListState()
            listState.interactionSource
            LazyColumn(state = listState) {
                item {
                    Card(Modifier.padding(horizontal = 14.dp)) {
                        Column {
                            Row {
                                Row(Modifier.weight(1F).padding(19.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Image(painterResource(R.mipmap.mtp_today_study_time), modifier = Modifier.size(40.dp), contentDescription = null)
                                    Spacer(Modifier.width(10.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(if (vm.isLogin) DateUtils.secondToHoursMinute(studyInfoBean.playTimeToday) else "", fontSize = 14.sp, color = color_2)
                                        Spacer(Modifier.width(5.dp))
                                        Text("今日", Modifier.background(color_47A3FF, CircleShape).padding(horizontal = 5.dp), fontSize = 10.sp, color = Color.White)
                                    }

                                }
                                Row(Modifier.weight(1F).padding(19.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Image(painterResource(R.mipmap.mtp_today_study_question), modifier = Modifier.size(40.dp), contentDescription = null)
                                    Spacer(Modifier.width(10.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(if (vm.isLogin) "${studyInfoBean.examinationToday}题" else "", fontSize = 14.sp, color = color_2)
                                        Spacer(Modifier.width(5.dp))
                                        Text("今日", Modifier.background(color_47A3FF, CircleShape).padding(horizontal = 5.dp), fontSize = 10.sp, color = Color.White)
                                    }
                                }
                            }
                            Row {
                                Column(Modifier.weight(1F), horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("学习时长", fontSize = 12.sp, color = color_b)
                                    Spacer(Modifier.height(10.dp))
                                    Text(if (vm.isLogin) DateUtils.secondToHoursMinute(studyInfoBean.playTimeAll) else "/", fontSize = 16.sp, color = color_2, fontWeight = FontWeight.Bold)
                                    Spacer(Modifier.height(23.dp))
                                }
                                Column(Modifier.weight(1F), horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("累计做题", fontSize = 12.sp, color = color_b)
                                    Spacer(Modifier.height(10.dp))
                                    Text(if (vm.isLogin) DateUtils.secondToHoursMinute(studyInfoBean.examinationAll) else "/", fontSize = 16.sp, color = color_2, fontWeight = FontWeight.Bold)
                                }
                                Column(Modifier.weight(1F), horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("看过资料", fontSize = 12.sp, color = color_b)
                                    Spacer(Modifier.height(10.dp))
                                    Text("/", fontSize = 16.sp, color = color_2, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                    }
                    Spacer(Modifier.height(10.dp))
                    Row(Modifier.fillMaxWidth()) {
                        StudyIconItem(Modifier.weight(1F), "课程直播", R.mipmap.mtp_study_live)
                        StudyIconItem(Modifier.weight(1F), "离线课程", R.mipmap.mtp_study_offline_video)
                        StudyIconItem(Modifier.weight(1F), "错题本", R.mipmap.mtp_question_bank_error)
                        StudyIconItem(Modifier.weight(1F), "试题收藏", R.mipmap.mtp_question_bank_collection)
                    }
                    Box(Modifier.background(color_F7F7F7).height(5.dp).fillMaxWidth())
                }
                item {
                    TabRow(vm.categoryIndex, Modifier.height(40.dp).fillMaxWidth(0.4F), backgroundColor = Color.White, indicator = { positions ->//设置滑动条的属性，默认是白色的
                        Box(Modifier.myTabIndicatorOffset(positions[vm.categoryIndex]).fillMaxWidth().height(3.dp).background(color_47A3FF, CircleShape))
                    }, contentColor = Color.White) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.noClickable {
                            vm.categoryIndex = 0
                        }) {
                            Text("课程", fontSize = if (vm.categoryIndex == 0) 14.sp else 12.sp, color = Color.Black)
                        }
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.noClickable {
                            vm.categoryIndex = 1
                        }) {
                            Text("题库", fontSize = if (vm.categoryIndex == 1) 14.sp else 12.sp, color = Color.Black)
                        }
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.noClickable {
                            vm.categoryIndex = 2
                        }) {
                            Text("资料", fontSize = if (vm.categoryIndex == 2) 14.sp else 12.sp, color = Color.Black)
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                }
                when (vm.categoryIndex) {
                    0 -> {
                        item {
                            //课程
                            CoursePage(vm)
                        }
                    }
                    1 -> {
                        item {
                            //题库
                            QuestionPage(listState)
                        }
                    }
                    2 -> {
                        //资料

                    }
                }
            }

        }
    }
}

@Composable
fun StudyIconItem(modifier: Modifier, title: String, @DrawableRes resId: Int) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(10.dp))
        Image(painterResource(resId), title, Modifier.size(26.dp))
        Spacer(Modifier.height(8.dp))
        Text(title, color = color_2, fontSize = 12.sp)
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
fun StudyCourseItem(userCourseBean: UserCourseBean, mNowTime: Long) {
    if (userCourseBean.courseBean != null) {
        Row(Modifier.padding(start = 15.dp, end = 22.dp, top = 7.dp, bottom = 7.dp)) {
            Box {
                AsyncImage(model = userCourseBean.courseBean!!.thumb, contentDescription = null, Modifier.width(123.dp).aspectRatio(123 / 69F))
                Box(Modifier.padding(end = 3.dp, bottom = 3.dp).align(Alignment.BottomEnd)) {
                    Text(dealCourseTime(userCourseBean.endTime, userCourseBean.isRefund, mNowTime), Modifier.background(Color.Black, RoundedCornerShape(2.dp)).padding(horizontal = 3.dp), fontSize = 9.sp, color = Color.White)
                }

            }
            Spacer(Modifier.width(15.dp))
            Column(Modifier.weight(1F).height(69.dp)) {
                Text(userCourseBean.courseBean!!.courseName, Modifier.fillMaxWidth(), color = color_2, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(5.dp))
                Row {
                    Text(userCourseBean.learnCount.toString(), fontSize = 12.sp, color = color_2)
                    Text("/${userCourseBean.courseBean!!.classCount}", fontSize = 12.sp, color = color_b)
                }
                Spacer(Modifier.weight(1F))
                Row {
                    if (dealCourseTime(userCourseBean.endTime, userCourseBean.isRefund, mNowTime) == "已过期" || dealCourseTime(userCourseBean.endTime, userCourseBean.isRefund, mNowTime) == "已退课") {
                        Spacer(Modifier.weight(1F))
                        Text("重新购买", color = color_47A3FF, fontSize = 10.sp)
                    } else {
                        Text(dealStartOrResume(userCourseBean), Modifier.weight(1F), fontSize = 12.sp, color = color_47A3FF, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Spacer(Modifier.width(4.dp))
                        Image(painterResource(R.mipmap.mtp_item_video_select), contentDescription = null)
                    }

                }

            }

        }
    }

}

@Composable
fun StudyVideoItem(videoInfoBean: VideoInfoBean) {
    Row(Modifier.padding(start = 15.dp, end = 22.dp, top = 7.dp, bottom = 7.dp)) {
        Box {
            AsyncImage(model = videoInfoBean.goodsThumb, contentDescription = null, Modifier.width(123.dp).aspectRatio(123 / 69F).clip(RoundedCornerShape(5.dp)))
        }
        Spacer(Modifier.width(15.dp))
        Column(Modifier.weight(1F).height(69.dp)) {
            Text(videoInfoBean.goodsName, Modifier.fillMaxWidth(), color = color_2, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(6.dp))
            Row {
                val videoLearnRecordBean = videoInfoBean.videoLearnRecordBean
                Text("总时长：${DateUtils.changeVideoTime(videoInfoBean.catLength ?: 0)}", modifier = Modifier.weight(1F), fontSize = 12.sp, color = color_b)
                Text(dealStudyTime(videoLearnRecordBean, videoInfoBean.catLength ?: 0), fontSize = 12.sp, color = color_b)
                Spacer(Modifier.width(26.dp))
            }
            Spacer(Modifier.weight(1F))
            Text("${videoInfoBean.clickCount}人学习", fontSize = 12.sp, color = color_b, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun CoursePage(vm: MTPCStudyVM) {
    val pagerState = rememberPagerState(0)
    Row {
        Spacer(Modifier.width(7.dp))
        Box(Modifier.background(if (vm.courseIndex == 0) Color.White else color_E0E0E0, shape = CircleShape).border(width = 0.5.dp, color = if (vm.courseIndex == 0) color_47A3FF else Color.Transparent, CircleShape).padding(horizontal = 14.dp, vertical = 5.dp).noClickable {
            vm.courseIndex = 0
            vm.launch({
                pagerState.scrollToPage(vm.courseIndex)
            })
        }) {
            Text("套课", fontSize = 12.sp, color = if (vm.courseIndex == 0) color_47A3FF else color_2)
        }
        Spacer(Modifier.width(14.dp))
        Box(Modifier.background(if (vm.courseIndex == 1) Color.White else color_E0E0E0, shape = CircleShape).border(width = 0.5.dp, color = if (vm.courseIndex == 1) color_47A3FF else Color.Transparent, CircleShape).padding(horizontal = 14.dp, vertical = 5.dp).noClickable {
            vm.courseIndex = 1
            vm.launch({
                pagerState.scrollToPage(vm.courseIndex)
            })
        }) {
            Text("小课", fontSize = 12.sp, color = if (vm.courseIndex == 1) color_47A3FF else color_2)
        }
    }
    HorizontalPager(2, modifier = Modifier.heightIn(min = 100.dp), state = pagerState, verticalAlignment = Alignment.Top, userScrollEnabled = false) { index ->
        when (index) {
            0 -> {
                Column {
                    if (vm.userCourses.isEmpty()) {
                        Spacer(Modifier.height(50.dp))
                        Box {
                            Row(Modifier.align(Alignment.Center), verticalAlignment = Alignment.CenterVertically) {
                                Text("发现更多课程", color = color_47A3FF, fontSize = 12.sp)
                                Spacer(Modifier.width(4.dp))
                                Image(painterResource(R.mipmap.mtp_home_more_subject), modifier = Modifier.size(12.dp), contentDescription = null)
                            }
                        }
                    } else {
                        vm.userCourses.forEach { userCourse ->
                            StudyCourseItem(userCourse, vm.nowTime)
                        }
                    }
                }
            }
            1 -> {
                Column {
                    if (vm.userVideos.isEmpty()) {
                        Spacer(Modifier.height(50.dp))
                        Box {
                            Row(Modifier.align(Alignment.Center), verticalAlignment = Alignment.CenterVertically) {
                                Text("发现更多课程", color = color_47A3FF, fontSize = 12.sp)
                                Spacer(Modifier.width(4.dp))
                                Image(painterResource(R.mipmap.mtp_home_more_subject), modifier = Modifier.size(12.dp), contentDescription = null)
                            }
                        }
                    } else {
                        vm.userVideos.forEach { userVideo ->
                            StudyVideoItem(userVideo)
                        }
                        HomeBottom()
                    }
                }
            }
        }
    }
}

@Composable
@ExperimentalPagerApi
fun QuestionPage(state: LazyListState,vm: MTPCStudyQuestionVM = viewModel()) {

    Column() {
        Spacer(Modifier.height(10.dp))
        Row(Modifier.padding(horizontal = 7.dp)) {
            vm.userQuestions.forEachIndexed { index, courseBean ->
                val isSelect = index == vm.questionIndex
                Box(Modifier.background(if (isSelect) Color.White else color_E0E0E0).border(width = 0.5.dp, color = if (isSelect) color_47A3FF else Color.Transparent, shape = CircleShape).padding(horizontal = 14.dp, vertical = 5.dp)) {
                    Text(courseBean.courseName, fontSize = 12.sp, color = if (isSelect) color_47A3FF else color_2)
                }
            }
        }
        SwipeRefresh(state = rememberSwipeRefreshState(vm.refreshing), onRefresh = {
            vm.getData()
        }, swipeEnabled = state.firstVisibleItemScrollOffset==0) {
            LazyColumn(Modifier.height(300.dp)) {
                item {
                    if (vm.userQuestions.isNotEmpty()) {
                        HorizontalPager(vm.userQuestions.size) { index ->
                            QuestionItemPage(vm.userQuestions[index].courseId)
                        }
                    }
                }

            }
        }


    }

}

@Composable
@ExperimentalPagerApi
fun QuestionItemPage(courseId: Int, vm: MTPCStudyQuestionItemVM = viewModel()) {
    vm.getUserBuyCourseTopics(courseId)
    Column() {
        vm.studyQuestionTypes.forEachIndexed { index, it ->
            Column() {
                Row(Modifier.padding(start = 15.dp, top = 20.dp, bottom = 20.dp, end = 23.dp)) {
                    Image(painterResource(it.resId), contentDescription = null, Modifier.size(24.dp, 30.dp))
                    Spacer(Modifier.width(20.dp))
                    Column(Modifier.weight(1F)) {
                        Text(it.name, fontSize = 13.sp)
                        Text(it.topicBeans.size.toString(), fontSize = 12.sp)
                    }
                    Image(painterResource(if (it.fold) R.mipmap.mtp_arrow_down else R.mipmap.mtp_arrow_up), contentDescription = null, Modifier.size(13.dp).align(Alignment.CenterVertically).noClickable {
                        val data = vm.studyQuestionTypes
                        data[index].fold = !data[index].fold
                        vm.studyQuestionTypes = listOf()
                        vm.studyQuestionTypes = data
                    })
                }
            }
        }
    }

}

//处理有效性显示
fun dealCourseTime(endTime: String?, isRefund: Int, timestamp: Long): String {
    return if (endTime == null || endTime.isEmpty()) {
        "长期有效"
    } else {
        if (isRefund == 1) {
            "已退课"
        } else {
            if (timestamp > DateUtils.utcToTimestamp(endTime)) {
                "已过期"
            } else {
                "有效期：" + DateUtils.utcToTime(endTime, "yyyy/MM/dd")
            }
        }
    }
}

//开始学习或继续学习
fun dealStartOrResume(userCourseBean: UserCourseBean): String {
    val courseBean = userCourseBean.courseBean

    val learnRecordBean = userCourseBean.learnRecordBean
    if (learnRecordBean != null) {
        val courseGoodsEntityBean = learnRecordBean.courseGoodsEntity
        if (courseGoodsEntityBean != null) {
            return "继续学习：" + courseGoodsEntityBean.goodsName
        }
    }

    if (courseBean != null) {
        val catalogLists = courseBean.catalogList
        if (!catalogLists.isNullOrEmpty()) {
            return "开始学习：" + catalogLists[0].catalogName
        }
    }
    return ""
}

//学习时长显示
fun dealStudyTime(videoLearnRecordBean: VideoLearnRecordBean?, catLength: Int): String {
    return if (videoLearnRecordBean == null) {
        ""
    } else {
        if (videoLearnRecordBean.playTime ?: 0 > catLength) {
            "已学完"
        } else {
            "已学${DateUtils.changeVideoTime(videoLearnRecordBean.playTime ?: 0)}"
        }

    }
}


fun Modifier.myTabIndicatorOffset(currentTabPosition: TabPosition): Modifier = composed(inspectorInfo = debugInspectorInfo {
    name = "tabIndicatorOffset"
    value = currentTabPosition
}) {
    val currentTabWidth by animateDpAsState(targetValue = 20.dp, animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing))
    val indicatorOffset by animateDpAsState(targetValue = currentTabPosition.left + (currentTabPosition.width - 20.dp) / 2, animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing))
    fillMaxWidth().wrapContentSize(Alignment.BottomStart).offset(x = indicatorOffset).width(currentTabWidth)
}