package com.mingtao.professionedu.ui.compose.login.view

import android.widget.Toast
import androidx.activity.compose.setContent
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.gyf.immersionbar.ImmersionBar
import com.mingtao.professionedu.R
import com.mingtao.professionedu.base.activity.BaseMTPCActivity
import com.mingtao.professionedu.data.model.LoginBean
import com.mingtao.professionedu.ui.compose.login.viewmodel.MTPCPasswordLoginVM
import com.mingtao.professionedu.ui.compose.theme.ComposeMTPTheme
import com.zheng.comon.arouter.RouterPath
import com.zheng.comon.error.ErrorManager
import com.zheng.comon.utils.SharedPreferencesUtils
import com.zheng.lib.data.error.Error
import com.zheng.lib.data.model.Resource
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
@Route(path = RouterPath.MTP_PATH_LOGIN_PASSWORD)
class MTPCPasswordLoginActivity : BaseMTPCActivity<MTPCPasswordLoginVM>() {


    @Autowired
    @JvmField
    var phone: String? = null

    override fun initialization() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.color_f).statusBarDarkFont(true).init()
        ARouter.getInstance().inject(this)
        setContent {
            ComposeMTPTheme {
                PasswordLoginPage(mViewModel)
            }
        }

        mViewModel.phoneNumber = phone?:""

    }

    override fun handleData(resource: Resource<*>) {
        when (resource) {
            is Resource.Loading -> showLoading()
            is Resource.Success -> {
                showLoadSuccess()
                if (resource.methodName == "passwordLogin") {
                    //登录成功后的操作
                    if (resource.data != null) {
                        val data = resource.data as LoginBean
                        SharedPreferencesUtils.saveString("phone", mViewModel.phoneNumber)
                        SharedPreferencesUtils.saveString("userPassword", mViewModel.password)
                        SharedPreferencesUtils.saveBoolean("pw_remember", mViewModel.rememberChecked)
                        SharedPreferencesUtils.saveInt("loginType", 1)
                        SharedPreferencesUtils.saveString("token", data.rememberToken)

                        if (data.intentionCourseId == "") {

                        } else {
                            //前往首页
                            ARouter.getInstance().build(RouterPath.MTP_PATH_MAIN).navigation()
                            finish()
                        }
                    }
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