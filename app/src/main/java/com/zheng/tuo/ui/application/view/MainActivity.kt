package com.zheng.tuo.ui.application.view

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.zheng.lib.base.viewmodel.EmptyViewModel
import com.zheng.lib.base.activity.BaseActivity
import com.zheng.lib.databinding.ActivityNoBinding
import com.zheng.tuo.R
import com.zheng.tuo.arouter.RouterPath
import com.zheng.tuo.databinding.ActivityMainBinding
import com.zheng.tuo.ui.application.viewmodel.MainVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = RouterPath.PATH_MAIN)
class MainActivity : BaseActivity<ActivityMainBinding, MainVM>(R.layout.activity_main) {
    override fun initialization() {


        binding.rbHome.isChecked = true
        //
        binding.rgTab.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                //选中
                R.id.rb_home -> {

                }
                R.id.rb_course -> {

                }
                R.id.rb_live -> {

                }
                R.id.rb_study -> {

                }
                R.id.rb_my -> {}
            }
        }
    }

}