package com.mingtao.professionedu.ui.compose.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.gyf.immersionbar.ImmersionBar
import com.mingtao.professionedu.R
import com.mingtao.professionedu.ui.compose.main.viewmodel.MTPCStudyVM
import com.mingtao.professionedu.ui.compose.theme.color_2
import com.mingtao.professionedu.ui.compose.theme.color_47A3FF
import com.mingtao.professionedu.ui.compose.theme.color_4DCDFD
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
            Card(Modifier.padding(horizontal = 14.dp)) {
                Row {
                    Row(Modifier.weight(1F).padding(19.dp),verticalAlignment = Alignment.CenterVertically) {
                        Image(painterResource(R.mipmap.mtp_today_study_time), modifier = Modifier.size(40.dp), contentDescription = null)
                        Spacer(Modifier.width(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(if (vm.isLogin) DateUtils.secondToHoursMinute(studyInfoBean.playTimeToday) else "", fontSize = 14.sp, color = color_2)
                            Spacer(Modifier.width(5.dp))
                            Text("今日", Modifier.background(color_47A3FF, CircleShape).padding(horizontal = 5.dp), fontSize = 10.sp, color = Color.White)
                        }

                    }
                    Row(Modifier.weight(1F).padding(19.dp),verticalAlignment = Alignment.CenterVertically) {
                        Image(painterResource(R.mipmap.mtp_today_study_question), modifier = Modifier.size(40.dp), contentDescription = null)
                        Spacer(Modifier.width(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(if (vm.isLogin) "${studyInfoBean.examinationToday}题" else "", fontSize = 14.sp, color = color_2)
                            Spacer(Modifier.width(5.dp))
                            Text("今日", Modifier.background(color_47A3FF, CircleShape).padding(horizontal = 5.dp), fontSize = 10.sp, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}