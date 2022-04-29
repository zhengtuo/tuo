package com.mingtao.professionedu.ui.compose.main.view

import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.accompanist.pager.ExperimentalPagerApi
import com.gyf.immersionbar.ImmersionBar
import com.jeremyliao.liveeventbus.LiveEventBus
import com.mingtao.professionedu.base.activity.BaseMTPCActivity
import com.mingtao.professionedu.ui.compose.main.viewmodel.MTPCMainVM
import com.mingtao.professionedu.ui.compose.theme.ComposeMTPTheme
import com.zheng.base.data.model.Resource
import com.zheng.comon.arouter.RouterPath
import com.zheng.comon.error.ErrorManager
import com.zheng.lib.data.error.Error
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@AndroidEntryPoint
@Route(path = RouterPath.MTP_PATH_MAIN)
class MTPCMainActivity : BaseMTPCActivity<MTPCMainVM>() {


    override fun initialization() {
        ImmersionBar.with(this).statusBarDarkFont(true).init()
        setContent {
            ComposeMTPTheme {
                //去掉滑动阴影
                CompositionLocalProvider(
                    LocalOverScrollConfiguration provides null,
                ) {
                    Scaffold(bottomBar = {
                        BottomTab(selected = mViewModel.selectedTab, onSelectedChanged = {
                            mViewModel.selectedTab = it
                        })
                    }) {
                        Box(Modifier.padding(it)) {
                            when (mViewModel.selectedTab) {
                                0 -> HomePage()
                                1 -> StudyPage()
                            }
                        }

                    }
                }

            }
        }
    }

    override fun setupListener() {
        LiveEventBus.get("handleData", Resource::class.java).observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> showLoading()
                is Resource.DataError -> {
                    showLoadFailed()
                    resource.errorCode?.let {
                        if (it == Error.HAVE_MESSAGE) {
                            Toast.makeText(mContext, resource.errorCase ?: "", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(mContext, ErrorManager.getError(it).description, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                is Resource.Complete -> showLoadSuccess()
                else -> {

                }
            }
        }
    }
}
