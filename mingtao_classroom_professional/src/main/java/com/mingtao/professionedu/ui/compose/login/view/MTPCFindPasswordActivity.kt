package com.mingtao.professionedu.ui.compose.login.view

import android.widget.Toast
import androidx.activity.compose.setContent
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.gyf.immersionbar.ImmersionBar
import com.jeremyliao.liveeventbus.LiveEventBus
import com.mingtao.professionedu.R
import com.mingtao.professionedu.base.activity.BaseMTPCActivity
import com.mingtao.professionedu.ui.compose.login.viewmodel.MTPCFindPasswordVM
import com.mingtao.professionedu.ui.compose.theme.ComposeMTPTheme
import com.zheng.comon.arouter.RouterPath
import com.zheng.comon.error.ErrorManager
import com.zheng.comon.utils.SharedPreferencesUtils
import com.zheng.lib.data.error.Error
import com.zheng.lib.data.model.Resource
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
@Route(path = RouterPath.MTP_PATH_FIND_PASSWORD)
class MTPCFindPasswordActivity : BaseMTPCActivity<MTPCFindPasswordVM>() {

    override fun initialization() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.color_f).statusBarDarkFont(true).init()
        ARouter.getInstance().inject(this)
        setContent {
            ComposeMTPTheme {
                FindPasswordPage(mViewModel)
            }
        }
    }


    override fun handleData(resource: Resource<*>) {
        when (resource) {
            is Resource.Loading -> showLoading()
            is Resource.Success -> {
                showLoadSuccess()
                if (resource.methodName == "sendSms") {
                    //发送成功的操作
                    mViewModel.countDown()
                } else if (resource.methodName == "changePassword") {
                    //登录成功后的操作
                    Toast.makeText(applicationContext, "密码重置成功，请重新登录", Toast.LENGTH_SHORT).show()
                    SharedPreferencesUtils.saveString("userPassword", "")
                    SharedPreferencesUtils.saveInt("loginType", 0)
                    ARouter.getInstance().build(RouterPath.MTP_PATH_MAIN).navigation()
                    LiveEventBus.get("finish").post("")
                    finish()
                }
            }
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
            else -> {

            }
        }
    }
}