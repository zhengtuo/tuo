package com.mingtao.professionedu.ui.compose.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.times
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.forjrking.drawable.Shape
import com.mingtao.professionedu.R
import com.mingtao.professionedu.ui.compose.theme.color_e
import com.mingtao.professionedu.ui.compose.theme.color_f

@Composable
fun HomePage(num: Int) {
    //垂直排列元素
    Column(
        Modifier
            .background(color_f)
            .fillMaxSize()
    ) {
        HomeSearch(num)
    }
}

@Composable
fun HomeSearch(num: Int) {
    //水平排列元素
    Row(Modifier.padding(start = 14.dp, end = 14.dp, top = 8.dp)) {
        Box(
            Modifier
                .background(color_e, CircleShape)
                .height(28.dp)
                .padding(start = 10.dp)
                .weight(1F)

        ) {
            Image(
                painterResource(R.mipmap.ic_search), "搜索",
                Modifier
                    .size(12.dp)
                    .align(Alignment.CenterStart)
            )
        }
        Spacer(Modifier.width(14.dp))
        Box(Modifier.align(Alignment.CenterVertically)) {
            Image(
                painterResource(R.mipmap.home_message), "搜索",
                Modifier
                    .width(25.dp)
                    .height(29.dp)
                    .padding(5.dp)
            )
            Text(
                text = num.toString(), color = Color.White, fontSize = 7.sp, modifier = Modifier
                    .background(Color.Red, CircleShape)
                    .size(10.dp)
                    .align(Alignment.TopEnd), textAlign = TextAlign.Center
            )
        }
    }
}

fun Modifier.redPoint(num: Int): Modifier = this.drawWithContent {
    drawContent()
    drawIntoCanvas {
        if (num > 0) {
            drawCircle(Color.Red, 5.dp.toPx(), Offset(size.width - 1.dp.toPx(), 1.dp.toPx()))
            val nativePaint = android.graphics.Paint().let {
                it.apply {
                    textSize = size.width / 2
                    color = android.graphics.Color.WHITE.and(2)
                }
            }
            drawContext.canvas.nativeCanvas.drawText(num.toString(), size.width / 4 * 3, size.height / 4, nativePaint).apply {

            }
        }
    }

}