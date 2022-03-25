package com.mingtao.professionedu.ui.compose.login.view

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mingtao.professionedu.ui.compose.login.viewmodel.MTPCLoginVM
import com.mingtao.professionedu.ui.compose.theme.ComposeMTPTheme
import com.zheng.base.activity.BaseComposeActivity
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class MTPCLoginActivity : BaseComposeActivity<MTPCLoginVM>() {
    override fun initialization() {
        setContent {
            mViewModel = viewModel()
            ComposeMTPTheme {
                Column {
                    LoginPage(mViewModel)
                }
            }
        }
    }
}