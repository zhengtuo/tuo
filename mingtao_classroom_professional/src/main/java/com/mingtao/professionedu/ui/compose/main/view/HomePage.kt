package com.mingtao.professionedu.ui.compose.main.view

import android.text.TextUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.accompanist.pager.*
import com.gyf.immersionbar.ImmersionBar
import com.mingtao.professionedu.R
import com.mingtao.professionedu.data.model.*
import com.mingtao.professionedu.ui.compose.main.viewmodel.MTPCHomeVM
import com.mingtao.professionedu.ui.compose.theme.*
import com.zheng.comon.utils.CommonUtils
import com.zheng.comon.utils.DateUtils
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalPagerApi
@Composable
fun HomePage(vm: MTPCHomeVM = viewModel(),) {
    //HomeType一行显示几列
    val columnCount = 5
    //计算有几行
    val rows = (vm.homeTypes.value.size + columnCount - 1) / columnCount
    val statusBarHeight: Int = ImmersionBar.getStatusBarHeight(CommonUtils.context!!)
    //转换状态栏高度为dp
    val statusBarHeightDp = with(LocalDensity.current) {
        statusBarHeight.toDp()
    }


    //垂直排列元素
    Column(Modifier.background(color_f).fillMaxWidth().padding(top = statusBarHeightDp)) {
        HomeTopSearch(vm)
        Spacer(Modifier.height(10.dp))
        LazyColumn {
            item {
                HomeBanner(vm)
            }
            if (vm.homeTypes.value.isNotEmpty()) {
                items(rows) { rowIndex ->
                    Row {
                        for (column in 0 until columnCount) {
                            //itemIndex List数据位置
                            val itemIndex = rowIndex * columnCount + column
                            HomeTypeItem(Modifier.weight(1F), if (itemIndex > vm.homeTypes.value.size) null else vm.homeTypes.value[itemIndex])
                        }
                    }
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
            }
            item {
                HomeArticle(vm)
            }
            if (vm.recommends.value.isNotEmpty()) {
                items(vm.recommends.value.size) { index ->
                    HomeRecommendItem(vm.recommends.value[index])
                }
            }

            if (vm.teachers.value.isNotEmpty()) {
                item {
                    HomeTeachers(vm)
                }
            }

            if (vm.guessYouLikes.value.isNotEmpty()) {
                item {
                    Row(Modifier.padding(start = 14.dp, top = 24.dp, end = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("猜你喜欢", Modifier.weight(1F), fontSize = 17.sp)
                        Text("没有喜欢的？点我选择意向课程", fontSize = 12.sp, color = color_b)
                        Spacer(Modifier.width(5.dp))
                        Image(painterResource(R.mipmap.mtp_home_item_more), modifier = Modifier.size(12.dp), contentDescription = null)
                    }
                }

                items(vm.guessYouLikes.value.size) { index ->
                    GuessYouLikeItem(vm.guessYouLikes.value[index])
                }
            }

            item {
                HomeBottom()
            }
        }
    }
}


@ExperimentalPagerApi
@Composable
fun HomeTopSearch(vm: MTPCHomeVM) {

    val pagerState = rememberPagerState(0)

    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        val timer = Timer()
        //延时器
        timer.schedule(object : TimerTask() {
            override fun run() {
                var current = pagerState.currentPage
                if (current == vm.hotSearchs.value.size - 1) {
                    current = 0
                } else {
                    current++
                }
                if (vm.hotSearchs.value.isNotEmpty()) {
                    coroutineScope.launch { pagerState.animateScrollToPage(current) }
                }
            }
        }, 3000, 3000)
        onDispose {
            timer.cancel()
        }
    }

    //水平排列元素
    Row(Modifier.padding(start = 14.dp, end = 14.dp, top = 8.dp)) {
        Box(Modifier.background(color_e, CircleShape).height(28.dp).padding(start = 10.dp).weight(1F)) {
            Image(painterResource(R.mipmap.mtp_search), "搜索", Modifier.size(12.dp).align(Alignment.CenterStart))
            VerticalPager(count = vm.hotSearchs.value.size, state = pagerState, horizontalAlignment = Alignment.Start, modifier = Modifier.padding(horizontal = 20.dp), userScrollEnabled = false) { index ->
                Text(text = vm.hotSearchs.value[index], color = color_b, fontSize = 12.sp, maxLines = 1)
            }
        }
        Spacer(Modifier.width(14.dp))
        Box(Modifier.align(Alignment.CenterVertically)) {
            Image(painterResource(R.mipmap.mtp_home_message), "搜索", Modifier.width(25.dp).height(29.dp).padding(5.dp))
            if (vm.messageSize > 0) {
                Text(text = vm.messageSize.toString(), color = Color.White, fontSize = 7.sp, modifier = Modifier.background(Color.Red, CircleShape).size(10.dp).align(Alignment.TopEnd), textAlign = TextAlign.Center)
            }

        }

    }
}

@ExperimentalPagerApi
@Composable
fun HomeBanner(vm: MTPCHomeVM) {

    val pagerState = rememberPagerState(0)

    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        val timer = Timer()
        //延时器
        timer.schedule(object : TimerTask() {
            override fun run() {
                var current = pagerState.currentPage
                if (current == vm.bannerLists.value.size - 1) {
                    current = 0
                } else {
                    current++
                }
                if (vm.bannerLists.value.isNotEmpty()) {
                    coroutineScope.launch { pagerState.animateScrollToPage(current) }
                }
            }
        }, 5200, 5200)
        onDispose {
            timer.cancel()
        }
    }
    Box {
        HorizontalPager(count = vm.bannerLists.value.size, state = pagerState, modifier = Modifier.padding(horizontal = 14.dp).clip(RoundedCornerShape(4.dp))) { index ->
            AsyncImage(model = vm.bannerLists.value[index].bannerThumb, contentDescription = vm.bannerLists.value[index].bannerName, modifier = Modifier.fillMaxWidth().aspectRatio(331 / 106F), contentScale = ContentScale.Crop)
        }
        HorizontalPagerIndicator(pagerState = pagerState, indicatorWidth = 4.dp, activeColor = color_f, inactiveColor = color_b, modifier = Modifier.align(Alignment.BottomCenter).padding(12.dp))

    }

}

@Composable
fun HomeTypeItem(modifier: Modifier, homeTypeBean: HomeTypeBean?) {
    if (homeTypeBean == null) {
        Box(modifier) {

        }
    } else {
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(13.dp))
            AsyncImage(model = homeTypeBean.thumb, contentDescription = null, modifier = Modifier.size(46.dp))
            Spacer(Modifier.height(6.dp))
            Text(homeTypeBean.homePageOperationCategoryName, fontSize = 12.sp)
        }
    }
}

@Composable
fun HomeArticle(vm: MTPCHomeVM) {
    Box(Modifier.fillMaxWidth().clip(RoundedCornerShape(5.dp)).padding(horizontal = 14.dp)) {
        Box(Modifier.fillMaxWidth().aspectRatio(331 / 62F).clip(RoundedCornerShape(5.dp)).background(color_F7F7F7).padding(start = 14.dp, end = 26.dp)) {
            Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                Image(painterResource(R.mipmap.mtp_news), contentDescription = null)
                Spacer(Modifier.width(14.dp))
                Column {
                    if (vm.articles.value.isNotEmpty()) {
                        Row {
                            Text(vm.articles.value[0].title, Modifier.weight(1F), maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 12.sp)
                            Spacer(Modifier.width(20.dp))
                            Text(text = if (DateUtils.isTimeToday(DateUtils.utcToTimestamp(vm.articles.value[0].createTime))) DateUtils.utcToHM(vm.articles.value[0].createTime) else DateUtils.utcToTime(vm.articles.value[0].createTime, "MM月dd日"), fontSize = 12.sp)
                        }
                    }
                    Spacer(Modifier.height(5.dp))
                    if (vm.articles.value.size > 1) {
                        Row {
                            Text(vm.articles.value[1].title, Modifier.weight(1F), maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 12.sp)
                            Spacer(Modifier.width(20.dp))
                            Text(text = if (DateUtils.isTimeToday(DateUtils.utcToTimestamp(vm.articles.value[1].createTime))) DateUtils.utcToHM(vm.articles.value[1].createTime) else DateUtils.utcToTime(vm.articles.value[1].createTime, "MM月dd日"), fontSize = 12.sp)
                        }
                    }
                }

            }

        }
    }
}


@ExperimentalPagerApi
@Composable
fun HomeRecommendItem(homeFloorModuleBean: HomeFloorModuleBean) {
    Column {
        Row(Modifier.padding(start = 14.dp, top = 24.dp, end = 14.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(homeFloorModuleBean.floorModuleName, Modifier.weight(1F), fontSize = 17.sp)
            Text(if (homeFloorModuleBean.jumpType == 1) "换一换" else "更多", fontSize = 12.sp, color = color_b)
            Spacer(Modifier.width(5.dp))
            Image(painterResource(R.mipmap.mtp_home_item_more), modifier = Modifier.size(12.dp), contentDescription = null)
        }

        val pagerState = rememberPagerState(0)

        val isCourse = homeFloorModuleBean.courseType == 1

        val count = if (isCourse) homeFloorModuleBean.courseEntityList!!.size else homeFloorModuleBean.courseGoodsEntityList!!.size
        val pageCount = (count + 4 - 1) / 4

        HorizontalPager(count = pageCount, state = pagerState) { index ->
            //Text(text = index.toString())
            Column(Modifier.padding(horizontal = 14.dp)) {
                for (column in 0 until 2) {
                    Row {
                        for (column2 in 0 until 2) {
                            //itemIndex List数据位置
                            val itemIndex = index * 4 + column * 2 + column2
                            if (isCourse) {
                                HomeRecommendCourseItem(Modifier.weight(1F), if (itemIndex >= count) null else homeFloorModuleBean.courseEntityList!![itemIndex])
                            } else {
                                HomeRecommendVideoItem(Modifier.weight(1F), if (itemIndex >= count) null else homeFloorModuleBean.courseGoodsEntityList!![itemIndex])
                            }
                            if (column2 == 0) {
                                Spacer(Modifier.width(15.dp))
                            }

                        }
                    }
                }

            }

        }
        Spacer(Modifier.height(10.dp))
        HorizontalPagerBorderIndicator(pagerState = pagerState, indicatorWidth = 7.dp, activeColor = color_b, inactiveColor = color_f, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun HomeRecommendCourseItem(modifier: Modifier, courseBean: CourseBean?) {
    if (courseBean == null) {
        Box(modifier) {

        }
    } else {
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(10.dp))
            AsyncImage(model = courseBean.thumb, contentDescription = null, modifier = Modifier.fillMaxSize().aspectRatio(158 / 89F).clip(RoundedCornerShape(5.dp)))
            Spacer(Modifier.height(6.dp))
            Text(courseBean.courseName, Modifier.fillMaxWidth(), fontSize = 11.sp, overflow = TextOverflow.Ellipsis, maxLines = 1)
            Spacer(Modifier.height(8.dp))
            Row {
                Text(text = if (courseBean.isFree == 1) "免费" else getCourseShowPrice(courseBean.isPromote, courseBean.promoteTime ?: "", courseBean.shopPrice, courseBean.promotePrice), fontSize = 12.sp, color = color_F51818)
                //Text 文字 中划线
                Spacer(Modifier.width(3.dp))
                Text(text = if (courseBean.isFree == 1) "" else "￥${courseBean.marketPrice}", Modifier.weight(1F).align(Alignment.Bottom), fontSize = 10.sp, textDecoration = TextDecoration.LineThrough, color = color_b)
                Text(text = "${courseBean.picCount + courseBean.payCount}人学习", Modifier.align(Alignment.Bottom), fontSize = 10.sp, color = color_b)
            }
            Spacer(Modifier.height(5.dp))
        }
    }
}

@Composable
fun HomeRecommendVideoItem(modifier: Modifier, videoInfoBean: VideoInfoBean?) {
    if (videoInfoBean == null) {
        Box(modifier) {

        }
    } else {
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(10.dp))
            AsyncImage(model = videoInfoBean.goodsThumb, contentDescription = null, modifier = Modifier.fillMaxSize().aspectRatio(158 / 89F).clip(RoundedCornerShape(5.dp)), contentScale = ContentScale.Crop)
            Spacer(Modifier.height(6.dp))
            Text(videoInfoBean.goodsName, Modifier.fillMaxWidth(), fontSize = 13.sp, overflow = TextOverflow.Ellipsis, maxLines = 1)
            Spacer(Modifier.height(8.dp))
            Row {
                Text(text = if (videoInfoBean.isFree == 1) "免费" else "￥${videoInfoBean.shopPrice}", fontSize = 12.sp, color = color_F51818)
                //Text 文字 中划线
                Spacer(Modifier.width(9.dp))
                Text(text = if (videoInfoBean.isFree == 1) "" else "￥${videoInfoBean.marketPrice}", Modifier.weight(1F).align(Alignment.Bottom), fontSize = 11.sp, textDecoration = TextDecoration.LineThrough, color = color_b)
                Text(text = "${videoInfoBean.clickCount}人学习", Modifier.align(Alignment.Bottom), fontSize = 11.sp, color = color_b)
            }
            Spacer(Modifier.height(5.dp))
        }
    }
}

@ExperimentalPagerApi
@Composable
fun HomeTeachers(vm: MTPCHomeVM) {
    Column {
        Row(Modifier.padding(start = 14.dp, top = 24.dp, end = 14.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("师资团队", Modifier.weight(1F), fontSize = 17.sp)
            Text("更多", fontSize = 12.sp, color = color_b)
            Spacer(Modifier.width(5.dp))
            Image(painterResource(R.mipmap.mtp_home_item_more), modifier = Modifier.size(12.dp), contentDescription = null)
        }

        val pagerState = rememberPagerState(0)

        val count = vm.teachers.value.size
        val pageCount = (count + 3 - 1) / 3

        Spacer(Modifier.height(10.dp))
        HorizontalPager(count = pageCount, state = pagerState) { index ->
            //Text(text = index.toString())
            Column(Modifier.padding(horizontal = 14.dp)) {

                Row {
                    for (column in 0 until 3) {
                        //itemIndex List数据位置
                        val itemIndex = index * 3 + column
                        HomeTeacherItem(Modifier.weight(1F), if (itemIndex > vm.teachers.value.size) null else vm.teachers.value[itemIndex])
                        if (column != 2) {
                            Spacer(Modifier.width(14.dp))
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        HorizontalPagerBorderIndicator(pagerState = pagerState, indicatorWidth = 7.dp, activeColor = color_b, inactiveColor = color_f, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun GuessYouLikeItem(videoInfoBean: VideoInfoBean) {
    Row(modifier = Modifier.padding(horizontal = 14.dp, vertical = 5.dp)) {
        AsyncImage(model = videoInfoBean.goodsThumb, contentDescription = null, modifier = Modifier.size(122.dp, 69.dp).clip(RoundedCornerShape(5.dp)), contentScale = ContentScale.Crop)
        Column(Modifier.padding(start = 10.dp).height(69.dp)) {
            Text(videoInfoBean.goodsName, Modifier.fillMaxWidth(), fontSize = 13.sp)
            Spacer(Modifier.height(6.dp))
            Text(videoInfoBean.keywords, Modifier.fillMaxWidth(), fontSize = 11.sp, overflow = TextOverflow.Ellipsis, maxLines = 1, color = color_b)
            Spacer(Modifier.weight(1F))
            Row {
                Text(text = if (videoInfoBean.isFree == 1) "免费" else "￥${videoInfoBean.shopPrice}", fontSize = 12.sp, color = color_F51818)
                //Text 文字 中划线
                Spacer(Modifier.width(9.dp))
                Text(text = if (videoInfoBean.isFree == 1) "" else "￥${videoInfoBean.marketPrice}", Modifier.weight(1F).align(Alignment.Bottom), fontSize = 11.sp, textDecoration = TextDecoration.LineThrough, color = color_b)
                Text(text = "${videoInfoBean.clickCount}人学习", Modifier.align(Alignment.Bottom), fontSize = 11.sp, color = color_b)
            }
        }
    }
}

@Composable
fun HomeTeacherItem(modifier: Modifier, teacherBean: TeacherBean?) {
    if (teacherBean == null) {
        Box(modifier) {

        }
    } else {
        Column(modifier.height(158.dp).background(color_F7F7F7, shape = RoundedCornerShape(5.dp)), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(20.dp))
            AsyncImage(model = teacherBean.teacherAvatar, contentDescription = null, modifier = Modifier.size(58.dp).clip(CircleShape), contentScale = ContentScale.Crop)
            Spacer(Modifier.height(13.dp))
            Text(teacherBean.teacherName, Modifier.fillMaxWidth(), fontSize = 13.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(6.dp))
            Text(teacherBean.expert, Modifier.fillMaxWidth().padding(horizontal = 3.dp), fontSize = 11.sp, overflow = TextOverflow.Ellipsis, maxLines = 2, color = color_b, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun HomeBottom() {
    Text(text = "—已经到底了—", Modifier.fillMaxWidth().padding(vertical = 10.dp), fontSize = 12.sp, textAlign = TextAlign.Center, color = color_b)
}

fun getCourseShowPrice(isPromote: Int, endTime: String, shopPrice: Double, promotePrice: Double): String {
    if (isPromote == 1) {
        if (TextUtils.isEmpty(endTime)) {
            return "￥$shopPrice"
        }
        if (DateUtils.utcToTimestamp(endTime) > Date().time) {
            return "￥$promotePrice"
        }
    }
    return "￥$shopPrice"
}

@ExperimentalPagerApi
@Composable
fun HorizontalPagerBorderIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    inactiveColor: Color = activeColor.copy(ContentAlpha.disabled),
    indicatorWidth: Dp = 8.dp,
    indicatorHeight: Dp = indicatorWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: Shape = CircleShape,

    ) {

    val indicatorWidthPx = LocalDensity.current.run {
        indicatorWidth.roundToPx()
    }
    val spacingPx = LocalDensity.current.run {
        spacing.roundToPx()
    }

    Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val indicatorModifier = Modifier.size(width = indicatorWidth, height = indicatorHeight).background(color = inactiveColor, shape = indicatorShape).border(width = 0.5.dp, color = color_b, shape = indicatorShape)

            repeat(pagerState.pageCount) {
                Box(indicatorModifier)
            }
        }

        Box(Modifier.offset {
            val scrollPosition = (pagerState.currentPage + pagerState.currentPageOffset).coerceIn(0f, (pagerState.pageCount - 1).coerceAtLeast(0).toFloat())
            IntOffset(x = ((spacingPx + indicatorWidthPx) * pagerState.currentPage).toInt(), y = 0)
        }.size(width = indicatorWidth, height = indicatorHeight).background(
            color = activeColor,
            shape = indicatorShape,
        ))
    }
}

