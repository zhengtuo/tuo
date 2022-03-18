package com.mingtao.professionedu.ui.compose.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mingtao.professionedu.ui.compose.theme.color_e
import com.mingtao.professionedu.ui.compose.theme.color_f

@Composable
fun HomePage() {
    Column(
        Modifier
            .background(color_f)
            .fillMaxSize()) {
        Column {
            Box(
                Modifier
                    .background(color_e, CircleShape)
                    .height(28.dp)
                    .weight(1F)) {

            }
        }

    }
}