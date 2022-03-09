package com.zheng.tuo.ui.application.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zheng.lib.base.fragment.BaseFragment

class MainFragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments: SparseArray<Fragment> = SparseArray()

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }
}