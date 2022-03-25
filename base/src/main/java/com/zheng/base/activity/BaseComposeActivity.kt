package com.zheng.base.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import com.zheng.lib.base.viewmodel.BaseViewModel
import com.zheng.lib.data.model.Resource
import com.zheng.lib.tool.gloading.Gloading
import com.zheng.lib.utils.observe


abstract class BaseComposeActivity<VM : BaseViewModel> : ComponentActivity() {

    @Suppress("UNCHECKED_CAST")
    lateinit var mViewModel: VM

    lateinit var mContext: Context
    lateinit var mActivity: Activity


    private var isFirstVisible = true

    protected var mHolder: Gloading.Holder? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = applicationContext
        mActivity = this
        initialization()
        setupListener()
        observeViewModel()
        initSingleEvent()
        initData()
    }

    //初始化方法
    abstract fun initialization()

    /**
     * 事情监听器
     */
    protected open fun setupListener() {

    }

    protected open fun observeViewModel() {
        observe(mViewModel.dataLiveData, ::handleData)
    }

    open fun handleData(resource: Resource<*>) {

    }

    /**
     * 初始化公共消息事件
     */
    protected fun initSingleEvent() {
        registerFinishEvent()
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        mViewModel.initData()
    }

    /**
     * 注册单个消息事件，消息对象:[Message]
     */
    protected open fun registerSingleLiveEvent(observer: Observer<Message?>) {
        mViewModel.getSingleLiveEvent().observe(this, observer)
    }

    open fun loadData() {

    }

    /**
     * make a Gloading.Holder wrap with current activity by default
     * override this method in subclass to do special initialization
     */
    protected open fun initLoadingStatusViewIfNeed() {
        if (mHolder == null) { //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(this).withRetry { onLoadRetry() }
        }
    }

    protected open fun onLoadRetry() { // override this method in subclass to do retry task
    }

    open fun showLoading() {
        initLoadingStatusViewIfNeed()
        mHolder?.showLoading()
    }

    open fun showLoadSuccess() {
        initLoadingStatusViewIfNeed()
        mHolder?.showLoadSuccess()
    }

    open fun showLoadFailed() {
        initLoadingStatusViewIfNeed()
        mHolder?.showLoadFailed()
    }

    /**
     * 关闭当前activity事件
     */
    protected open fun registerFinishEvent() {
        mViewModel.getFinishEvent().observe(this) {
            finish()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        if (!isFirstVisible) {
            loadData()
        }
        isFirstVisible = false
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}