package com.mingtao.professionedu.ui.main.view

import com.mingtao.professionedu.R
import com.mingtao.professionedu.databinding.FragmentMtpHomeBinding
import com.zheng.lib.base.fragment.BaseFragment
import com.zheng.lib.base.viewmodel.EmptyViewModel

//双重校验锁式
class MTPMyFragment private constructor() : BaseFragment<FragmentMtpHomeBinding, EmptyViewModel>(R.layout.fragment_mtp_home) {
    override fun initialization() {

    }

    companion object {
        val INSTANCE: MTPMyFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { MTPMyFragment() }
    }

}