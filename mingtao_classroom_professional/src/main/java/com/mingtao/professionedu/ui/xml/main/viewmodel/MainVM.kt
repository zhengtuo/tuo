package com.mingtao.professionedu.ui.xml.main.viewmodel

import androidx.databinding.ObservableField
import com.zheng.lib.base.model.EmptyModel
import com.zheng.lib.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(private val mModel: EmptyModel) : BaseViewModel() {
    var mSelect = ObservableField<Int>()

    init {

    }


}