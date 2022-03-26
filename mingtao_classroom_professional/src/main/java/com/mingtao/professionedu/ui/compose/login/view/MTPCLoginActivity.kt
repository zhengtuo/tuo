package com.mingtao.professionedu.ui.compose.login.view

import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mingtao.professionedu.ui.compose.login.viewmodel.MTPCLoginVM
import com.mingtao.professionedu.ui.compose.theme.ComposeMTPTheme
import com.zheng.base.activity.BaseComposeActivity
import com.zheng.lib.data.error.Error
import com.zheng.lib.data.model.Resource
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class MTPCLoginActivity : BaseComposeActivity<MTPCLoginVM>() {
    override fun initialization() {
        setContent {
            ComposeMTPTheme {
                Column {
                    LoginPage(mViewModel)
                }
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

                } else if (resource.methodName == "codeLogin") {
                    //登录成功后的操作


                }
            }
            is Resource.DataError -> {
                showLoadFailed()
                resource.errorCode?.let {
                    if (it == Error.HAVE_MESSAGE) {
                        Toast.makeText(mContext, resource.errorCase ?: "", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(mContext, mViewModel.errorManager.getError(it).description, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else -> {

            }
        }
    }
}