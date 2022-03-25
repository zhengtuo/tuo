package com.mingtao.professionedu.ui.xml.system.viewmodel

import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.zheng.lib.base.viewmodel.BaseViewModel
import com.zheng.lib.data.model.Resource
import com.zheng.lib.utils.LibUtils
import com.zheng.lib.utils.launch
import com.mingtao.professionedu.ui.xml.system.model.LoginModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MTPLoginVM @Inject constructor(private val mModel: LoginModel) : BaseViewModel() {

    var loginLiveData: MutableLiveData<Resource<Any>> = MutableLiveData()

    //phone
    val phoneText = ObservableField<String>()
    val codeText = ObservableField<String>()
    val smsEnable = ObservableField<Boolean>()
    val time = ObservableField<Int>()

    //协议
    var checked = ObservableField<Boolean>()

    //倒计时Job
    private var job: Job? = null

    init {
        smsEnable.set(true)
        checked.set(false)
    }

    fun sendSms(phone: String, code: String) {
        launch({
            loginLiveData.postValue(Resource.Loading())
            loginLiveData.postValue(withContext(Dispatchers.IO) {
                mModel.sendSms(phone, 3, code)
            })
        })
    }

    fun login(user_name: String, sms_code: String) {
        if (checked.get() == true) {
            launch({
                loginLiveData.postValue(Resource.Loading())
                loginLiveData.postValue(withContext(Dispatchers.IO) {
                    mModel.login(user_name, sms_code)
                })
            })
        } else {
            Toast.makeText(LibUtils.context, "请勾选隐私协议", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 60秒倒计时
     */
    fun countDown() {
        job?.cancel()
        job = launch({
            flow {
                (59 downTo 0).forEach {
                    delay(1000)
                    emit(it)
                }
            }.flowOn(Dispatchers.Default).onStart { // 倒计时开始 ，在这里可以让Button 禁止点击状态
                smsEnable.set(false)
                time.set(60)
            }.onCompletion { // 倒计时结束 ，在这里可以让Button 恢复点击状态
                smsEnable.set(true)
            }.collect { // 在这里 更新LiveData 的值来显示到UI
                time.set(it)
            }
        })
    }

    fun canLoginOrRegister(): Boolean {
        return phoneText.get()!!.length == 11 && codeText.get()!!.length == 6
    }

}