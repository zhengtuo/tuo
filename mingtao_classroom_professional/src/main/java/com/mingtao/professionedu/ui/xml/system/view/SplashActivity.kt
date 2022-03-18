package com.mingtao.professionedu.ui.xml.system.view

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.alibaba.android.arouter.facade.annotation.Route
import com.mingtao.professionedu.R
import com.mingtao.professionedu.databinding.ActivitySplashBinding
import com.zheng.lib.base.activity.BaseActivity
import com.zheng.lib.utils.SharedPreferencesUtils
import com.zheng.tuo.arouter.Router
import com.zheng.tuo.arouter.RouterPath
import com.mingtao.professionedu.ui.xml.system.viewmodel.SplashVM
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@Route(path = RouterPath.PATH_SPLASH)
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashVM>(R.layout.activity_splash) {
    //request permission
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission(), object : ActivityResultCallback<Boolean> {
        override fun onActivityResult(granted: Boolean) {
            if (granted) {
                // allow the permission
                if (SharedPreferencesUtils.getBoolean("privacy_policy_agree", false)) {
                    Router.getInstance().build(RouterPath.PATH_MAIN).navigation()
                } else {
                    Router.getInstance().build(RouterPath.PATH_LOGIN).navigation()
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