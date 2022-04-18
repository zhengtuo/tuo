package com.mingtao.professionedu.ui.compose.main.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.gyf.immersionbar.ImmersionBar
import com.mingtao.professionedu.R
import com.mingtao.professionedu.ui.compose.login.view.noClickable
import com.mingtao.professionedu.ui.compose.main.viewmodel.MTPCStudyVM
import com.mingtao.professionedu.ui.compose.theme.*
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
            TabRow(
                vm.categoryIndex, Modifier.height(50.dp).fillMaxWidth(0.4F), backgroundColor = Color.White,
                indicator = { positions ->//设置滑动条的属性，默认是白色的
                    TabRowDefaults.Indicator(Modifier.tabIndicatorOffset(positions[vm.categoryIndex]), color = Color.Red)
                },
            ) {
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