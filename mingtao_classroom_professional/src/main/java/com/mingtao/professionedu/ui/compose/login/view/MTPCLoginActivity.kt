package com.mingtao.professionedu.ui.compose.login.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.mingtao.professionedu.R
import com.mingtao.professionedu.ui.compose.login.viewmodel.LoginVM
import com.mingtao.professionedu.ui.compose.main.viewmodel.MainVM
import com.mingtao.professionedu.ui.compose.theme.ComposeMTPTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class MTPCLoginActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMTPTheme {
                Column {
                    val viewModel: LoginVM = viewModel()
                    LoginPage(viewModel)
                }
            }
        }
    }
}