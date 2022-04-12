package com.mingtao.professionedu.ui.compose.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.mingtao.professionedu.R
import com.mingtao.professionedu.data.model.HomeTypeBean
import com.mingtao.professionedu.ui.compose.main.viewmodel.MTPCHomeVM
import com.mingtao.professionedu.ui.compose.theme.color_F7F7F7
import com.mingtao.professionedu.ui.compose.theme.color_b
import com.mingtao.professionedu.ui.compose.theme.color_e
import com.mingtao.professionedu.ui.compose.theme.color_f
import com.zheng.comon.utils.DateUtils
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalPagerApi
@Composable
fun HomePage(vm: MTPCHomeVM = viewModel()) {
    //HomeType一行显示几列
    val columnCount = 5
    //计算有几行
    val rows = (vm.homeTypes.value.size + columnCount - 1) / columnCount

    //垂直排列元素
    Column(Modifier.background(color_f).fillMaxSize()) {
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
            Text(text = vm.messageSize.toString(), color = Color.White, fontSize = 7.sp, modifier = Modifier.background(Color.Red, CircleShape).size(10.dp).align(Alignment.TopEnd), textAlign = TextAlign.Center)
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

    HorizontalPager(count = vm.bannerLists.value.size, state = pagerState, modifier = Modifier.padding(horizontal = 14.dp).clip(RoundedCornerShape(4.dp))) { index ->
        AsyncImage(model = vm.bannerLists.value[index].bannerThumb, contentDescription = vm.bannerLists.value[index].bannerName, modifier = Modifier.fillMaxWidth().aspectRatio(331 / 106F), contentScale = ContentScale.Crop)
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