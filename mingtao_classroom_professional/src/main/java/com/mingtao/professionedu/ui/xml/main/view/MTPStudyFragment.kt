package com.mingtao.professionedu.ui.xml.main.view

import com.mingtao.professionedu.R
import com.mingtao.professionedu.databinding.FragmentMtpHomeBinding
import com.zheng.base.fragment.BaseFragment
import com.zheng.base.viewmodel.EmptyViewModel

//双重校验锁式
class MTPStudyFragment private constructor() : BaseFragment<FragmentMtpHomeBinding, EmptyViewModel>(R.layout.fragment_mtp_home) {
    override fun initialization() {

    }

    companion object {
        val INSTANCE: MTPStudyFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { MTPStudyFragment() }
    }

}