package com.zheng.tuo.ui.application.view

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.alibaba.android.arouter.facade.annotation.Route
import com.zheng.lib.base.activity.BaseActivity
import com.zheng.tuo.R
import com.zheng.tuo.arouter.Router
import com.zheng.tuo.arouter.RouterPath
import com.zheng.tuo.databinding.ActivitySplashBinding
import com.zheng.tuo.ui.application.viewmodel.SplashVM
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
                Router.getInstance().build(RouterPath.PATH_LOGIN).navigation()
            } else {
                //deny the permission

            }
            finish()
        }

    })


    override fun initialization() {

    }

    override fun onResume() {
        super.onResume()
        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

}