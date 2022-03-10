package com.zheng.tuo.ui.application.mt.main.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.zheng.lib.base.activity.BaseActivity
import com.zheng.tuo.R
import com.zheng.tuo.arouter.RouterPath
import com.zheng.tuo.databinding.ActivityMainBinding
import com.zheng.tuo.ui.application.mt.main.adapter.FragmentAdapter
import com.zheng.tuo.ui.application.mt.main.viewmodel.MainVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = RouterPath.PATH_MAIN)
class MainActivity : BaseActivity<ActivityMainBinding, MainVM>(R.layout.activity_main) {
    override fun initialization() {

        binding {

        }
        initAdapter()
    }

    override fun setupListener() {
        //RadioGroup 选中监听
        binding.rgTab.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                //选中
                R.id.rb_home -> {
                    binding.vp2.currentItem = 0
                }
                R.id.rb_course -> {
                    binding.vp2.currentItem = 1
                }
                R.id.rb_live -> {
                    binding.vp2.currentItem = 2
                }
                R.id.rb_study -> {
                    binding.vp2.currentItem = 3
                }
                R.id.rb_my -> {
                    binding.vp2.currentItem = 4
                }
            }
        }
    }

    private fun initAdapter() {
        val adapter = FragmentAdapter(this)
        adapter.setFragment(HomeFragment.instance, CourseFragment.instance, LiveFragment.instance, StudyFragment.instance, MyFragment.instance)
        binding.vp2.adapter = adapter
        binding.vp2.offscreenPageLimit = 5
        //禁止滑动
        binding.vp2.isUserInputEnabled = false
        binding.rbHome.isChecked = true
    }

}