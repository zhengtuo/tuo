package com.zheng.tuo.ui.application.mt.main.view

import com.zheng.lib.base.fragment.BaseFragment
import com.zheng.lib.base.viewmodel.EmptyViewModel
import com.zheng.tuo.R
import com.zheng.tuo.databinding.FragmentHomeBinding

//双重校验锁式
class CourseFragment private constructor() : BaseFragment<FragmentHomeBinding, EmptyViewModel>(R.layout.fragment_home) {
    override fun initialization() {

    }

    companion object {
        val instance: CourseFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { CourseFragment() }
    }

}