package com.zheng.tuo.ui.application.viewmodel

import com.zheng.lib.base.model.EmptyModel
import com.zheng.lib.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(private val mModel: EmptyModel) : BaseViewModel() {

}