package com.mingtao.professionedu.ui.compose.main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mingtao.professionedu.ui.compose.main.model.MTPCMainModel
import com.zheng.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@HiltViewModel
class MTPCMainVM @Inject constructor(private val mModel: MTPCMainModel) : BaseViewModel() {
    //选中tab
    var selectedTab by mutableStateOf(0)

}