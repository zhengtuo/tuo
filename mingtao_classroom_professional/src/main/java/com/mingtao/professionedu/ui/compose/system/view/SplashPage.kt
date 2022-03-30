package com.mingtao.professionedu.ui.compose.system.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.mingtao.professionedu.R

@Composable
fun SplashPage() {
    Image(painterResource(R.mipmap.splash), null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxWidth())
}