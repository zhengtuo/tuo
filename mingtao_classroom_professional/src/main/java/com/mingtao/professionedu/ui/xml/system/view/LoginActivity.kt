package com.mingtao.professionedu.ui.xml.system.view

import android.widget.Toast
import com.mingtao.professionedu.R
import com.mingtao.professionedu.base.activity.BaseMTPActivity
import com.mingtao.professionedu.databinding.ActivityLoginBinding
import com.mingtao.professionedu.ui.xml.system.viewmodel.MTPLoginVM
import com.zheng.lib.data.error.Error
import com.zheng.lib.data.model.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseMTPActivity<ActivityLoginBinding, MTPLoginVM>(R.layout.activity_login) {
    override fun initialization() {

        binding {
            vm = mViewModel
        }

        //binding.cbAgreement.buttonDrawable = DrawableDslManager.login_cb


//        binding.tvSend.setOnClickListener {
//            if (binding.etPhone.text.trim().toString() != "") {
//                mViewModel.getKey()
//            } else {
//                Toast.makeText(mContext, "请输入手机号", Toast.LENGTH_SHORT).show()
//            }
//
//        }

//        binding.tvLogin.setOnClickListener {
//            val phone = binding.etPhone.text.trim().toString()
//            val code = binding.etCode.text.trim().toString()
//            if (phone == "" || phone.length != 11) {
//                Toast.makeText(mContext, "请输入手机号", Toast.LENGTH_SHORT).show()
//            } else if (code == "" || code.length != 4) {
//                Toast.makeText(mContext, "请输入验证码", Toast.LENGTH_SHORT).show()
//            } else {
//                mViewModel.login(phone, code)
//            }
//        }
    }


    private fun handStateData(resource: Resource<*>) {
        when (resource) {
            is Resource.Loading -> showLoading()
            is Resource.Success -> {
                showLoadSuccess()
//                if (resource.methodName == "getKey") {
//                    val data = resource.data as KeyBean
//                    mViewModel.sendSms(binding.etPhone.text.trim().toString(), data.body ?: "")
//                } else if (resource.methodName == "sendSms") {
//                    //发送成功的操作
//                    mViewModel.countDown()
//                } else if (resource.methodName == "codeLogin") {
//                    //登录成功后的操作
//                    val data = resource.data as LoginBean
//                    listedSaveData(data)
//
//                }
            }
            is Resource.DataError -> {
                showLoadFailed()
                resource.errorCode?.let {
                    if (it == Error.HAVE_MESSAGE) {
                        Toast.makeText(mContext, resource.errorCase ?: "", Toast.LENGTH_SHORT).show()
                    } else {
                        //Toast.makeText(mContext, mViewModel.errorManager.getError(it).description, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else -> {

            }
        }
    }

}