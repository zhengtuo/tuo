package com.mingtao.professionedu.ui.compose.main.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mingtao.professionedu.R
import com.mingtao.professionedu.ui.compose.theme.color_3081e4
import com.mingtao.professionedu.ui.compose.theme.color_696969
import com.mingtao.professionedu.ui.compose.theme.color_f

@Composable
fun BottomTab(selected: Int, onSelectedChanged: (Int) -> Unit) {
    //val viewModel: MainVM = viewModel()
    Row(Modifier.background(color_f)) {
        TabItem(if (selected == 0) R.mipmap.home_select else R.mipmap.home_normal, if (selected == 0) color_3081e4 else color_696969, "首页",
            Modifier
                .weight(1F)
                .clickable {
                    onSelectedChanged(0)
                })
        TabItem(if (selected == 1) R.mipmap.study_select else R.mipmap.study_normal, if (selected == 1) color_3081e4 else color_696969, "学习",
            Modifier
                .weight(1F)
                .clickable {
                    onSelectedChanged(1)
                })
        TabItem(if (selected == 2) R.mipmap.news_select else R.mipmap.news_normal, if (selected == 2) color_3081e4 else color_696969, "发现",
            Modifier
                .weight(1F)
                .clickable {
                    onSelectedChanged(2)
                })
        TabItem(if (selected == 3) R.mipmap.user_select else R.mipmap.user_normal, if (selected == 3) color_3081e4 else color_696969, "我的",
            Modifier
                .weight(1F)
                .clickable {
                    onSelectedChanged(3)
                })
    }

}

@Composable
fun TabItem(@DrawableRes resId: Int, color: Color, title: String, modifier: Modifier = Modifier) {
    Column(modifier.padding(vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painterResource(resId), title, Modifier.size(24.dp))
        Text(title, fontSize = 11.sp, color = color)
    }
}