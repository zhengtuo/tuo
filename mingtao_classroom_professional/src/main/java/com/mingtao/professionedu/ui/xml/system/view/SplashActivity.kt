package com.mingtao.professionedu.ui.xml.system.view

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.mingtao.professionedu.R
import com.mingtao.professionedu.databinding.MtpActivitySplashBinding
import com.mingtao.professionedu.ui.xml.system.viewmodel.SplashVM
import com.zheng.base.activity.BaseActivity
import com.zheng.comon.utils.SharedPreferencesUtils
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<MtpActivitySplashBinding, SplashVM>(R.layout.mtp_activity_splash) {
    //request permission
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission(), object : ActivityResultCallback<Boolean> {
        override fun onActivityResult(granted: Boolean) {
            if (granted) {
                // allow the permission
                if (SharedPreferencesUtils.getBoolean("privacy_policy_agree", false)) {
                    //Router.getInstance().build(RouterPath.PATH_MAIN).navigation()
                } else {
                    //Router.getInstance().build(RouterPath.PATH_LOGIN).navigation()
                }
                finish()
            } else {
                //deny the permission

            }

        }

    })


    override fun initialization() {

    }

    override fun onResume() {
        super.onResume()
        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

}