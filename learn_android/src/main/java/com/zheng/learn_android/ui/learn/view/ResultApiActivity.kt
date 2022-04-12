package com.zheng.learn_android.ui.learn.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.zheng.base.activity.BaseActivity
import com.zheng.base.viewmodel.EmptyViewModel
import com.zheng.learn_android.R
import com.zheng.learn_android.databinding.ActivityResultApiBinding
import dagger.hilt.android.AndroidEntryPoint

@Suppress("ObjectLiteralToLambda")
@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class ResultApiActivity : BaseActivity<ActivityResultApiBinding, EmptyViewModel>(R.layout.activity_result_api) {

    companion object {
        const val result_code = 10
    }

    private val requestDataLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), object : ActivityResultCallback<ActivityResult> {
        override fun onActivityResult(result: ActivityResult?) {
            result?.apply {
                if (resultCode == result_code) {
                    val data = data?.getStringExtra("data")
                    //Handle data from SecondActivity
                    Toast.makeText(applicationContext, data.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    })

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission(), object : ActivityResultCallback<Boolean> {
        override fun onActivityResult(granted: Boolean) {
            if (granted) {
                // allow the permission
                Toast.makeText(applicationContext, "allow", Toast.LENGTH_SHORT).show()
            } else {
                //deny the permission
                Toast.makeText(applicationContext, "deny", Toast.LENGTH_SHORT).show()
            }
        }

    })

    private val requestFileLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument(), object : ActivityResultCallback<Uri> {
        override fun onActivityResult(result: Uri?) {

        }

    })

    private val getDataLauncher = registerForActivityResult(GetDataFromActivity(), object : ActivityResultCallback<String> {
        override fun onActivityResult(result: String?) {
            result.apply {
                Toast.makeText(mContext, result, Toast.LENGTH_LONG).show()
            }
        }
    })

    override fun initialization() {

        binding {
            title = "Activity Result API详解"
        }

        //在两个Activity之间交换数据
        binding.tvChange.setOnClickListener {
            val intent = Intent(this, GetResultActivity::class.java)
            requestDataLauncher.launch(intent)
        }

        //请求运行时权限
        binding.tvPermissions.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        /**
         * 内置Contract
         * StartActivityForResult()
         * StartIntentSenderForResult()
         * RequestMultiplePermissions()
         * RequestPermission()
         * TakePicturePreview()          调用手机摄像头去拍摄一张图片，并且得到这张图片的Bitmap对象
         * TakePicture()
         * TakeVideo()
         * PickContact()
         * GetContent()
         * GetMultipleContents()
         * OpenDocument()
         * OpenMultipleDocuments()
         * OpenDocumentTree()
         * CreateDocument()
         */
        binding.tvContract.setOnClickListener {

        }

        binding.tvCustomizeContract.setOnClickListener {
            getDataLauncher.launch(null)
        }


    }

}