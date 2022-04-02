package com.mingtao.professionedu.ui.compose.system.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.ImmersionBar
import com.mingtao.professionedu.ui.compose.theme.ComposeMTPTheme
import com.mingtao.professionedu.utils.MTPUtils
import com.zheng.base.utils.launch
import com.zheng.comon.arouter.RouterPath
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class MTPCSplashActivity : ComponentActivity() {

    //60秒倒计时Job
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).init()
        setContent {
            ComposeMTPTheme {
                SplashPage()
            }
        }
        countDown()

    }

    private fun jump() {
        if (MTPUtils.isLogin()) {
            ARouter.getInstance().build(RouterPath.MTP_PATH_MAIN).navigation()
        } else {
            ARouter.getInstance().build(RouterPath.MTP_PATH_LOGIN_CODE).withBoolean("show_jump", true).navigation()
        }
        finish()
    }

    /**
     * 60秒倒计时
     */
    fun countDown() {
        job?.cancel()
        job = launch({
            flow {
                (3 downTo 0).forEach {
                    delay(1000)
                    emit(it)
                }
            }.flowOn(Dispatchers.Default).onStart { // 倒计时开始 ，在这里可以让Button 禁止点击状态

            }.onCompletion { // 倒计时结束 ，在这里可以让Button 恢复点击状态
                jump()
            }.collect { // 在这里 更新LiveData 的值来显示到UI

            }
        })
    }
}