package com.mingtao.professionedu.ui.system.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zheng.composetest.ui.theme.ComposeTestTheme

class MTPLoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {

            }
        }
    }
}