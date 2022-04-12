package com.zheng.learn_android.ui.learn.view

import android.content.Intent
import com.zheng.base.activity.BaseActivity
import com.zheng.base.viewmodel.EmptyViewModel
import com.zheng.learn_android.R
import com.zheng.learn_android.databinding.ActivityGetResultBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GetResultActivity : BaseActivity<ActivityGetResultBinding, EmptyViewModel>(R.layout.activity_get_result) {

    override fun initialization() {
        binding {
            title = "跳转获取数据"
        }

        binding.tvBack.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data", "data from GetResultActivity")
            setResult(ResultApiActivity.result_code, intent)
            finish()
        }
    }
}