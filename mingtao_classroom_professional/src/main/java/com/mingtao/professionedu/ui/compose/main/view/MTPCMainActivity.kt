package com.mingtao.professionedu.ui.compose.main.view

import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mingtao.professionedu.ui.compose.main.viewmodel.MainVM
import com.mingtao.professionedu.ui.compose.theme.ComposeMTPTheme
import com.zheng.base.activity.BaseComposeActivity
import com.zheng.comon.arouter.RouterPath
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
@Route(path = RouterPath.MTP_PATH_MAIN)
class MTPCMainActivity : BaseComposeActivity<MainVM>() {

    override fun initialization() {
        setContent {
            ComposeMTPTheme {
                Scaffold(bottomBar = {
                    BottomTab(selected = mViewModel.selectedTab, onSelectedChanged = {
                        mViewModel.selectedTab = it
                    })
                }) {
                    when(mViewModel.selectedTab) {
                        0 -> HomePage(num = 0)
                        1 -> HomePage(num = 1)
                        2 -> HomePage(num = 2)
                        3 -> HomePage(num = 3)
                    }
                }
            }
        }
    }
}
