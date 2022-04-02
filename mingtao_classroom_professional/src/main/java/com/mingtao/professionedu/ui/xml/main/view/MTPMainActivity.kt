package com.mingtao.professionedu.ui.xml.main.view

import com.mingtao.professionedu.R
import com.mingtao.professionedu.databinding.MtpActivityMainBinding
import com.mingtao.professionedu.ui.xml.main.adapter.FragmentAdapter
import com.mingtao.professionedu.ui.xml.main.viewmodel.MainVM
import com.zheng.base.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MTPMainActivity : BaseActivity<MtpActivityMainBinding, MainVM>(R.layout.mtp_activity_main) {
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