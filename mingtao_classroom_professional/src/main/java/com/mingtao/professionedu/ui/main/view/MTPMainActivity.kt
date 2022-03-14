package com.mingtao.professionedu.ui.main.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.mingtao.professionedu.R
import com.mingtao.professionedu.databinding.ActivityMtpMainBinding
import com.zheng.lib.base.activity.BaseActivity
import com.zheng.tuo.arouter.RouterPath
import com.mingtao.professionedu.ui.main.adapter.FragmentAdapter
import com.mingtao.professionedu.ui.main.viewmodel.MainVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = RouterPath.PATH_MAIN)
class MTPMainActivity : BaseActivity<ActivityMtpMainBinding, MainVM>(R.layout.activity_mtp_main) {
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
                R.id.rb_study -> {
                    binding.vp2.currentItem = 1
                }
                R.id.rb_find -> {
                    binding.vp2.currentItem = 2
                }
                R.id.rb_my -> {
                    binding.vp2.currentItem = 3
                }
            }
        }
    }

    private fun initAdapter() {
        val adapter = FragmentAdapter(this)
        adapter.setFragment(MTPHomeFragment.INSTANCE, MTPStudyFragment.INSTANCE, MTPFindFragment.INSTANCE, MTPMyFragment.INSTANCE)
        binding.vp2.adapter = adapter
        binding.vp2.offscreenPageLimit = 4
        //禁止滑动
        binding.vp2.isUserInputEnabled = false
        binding.rbHome.isChecked = true
    }

}