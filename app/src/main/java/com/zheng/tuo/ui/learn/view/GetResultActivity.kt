package com.zheng.tuo.ui.learn.view

import android.content.Intent
import com.zheng.lib.base.activity.BaseActivity
import com.zheng.lib.base.viewmodel.EmptyViewModel
import com.zheng.tuo.R
import com.zheng.tuo.databinding.ActivityGetResultBinding
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