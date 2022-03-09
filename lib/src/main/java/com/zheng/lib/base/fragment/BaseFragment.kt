package com.zheng.lib.base.fragment


import android.content.Context
import android.os.Bundle
import android.os.Message
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.skydoves.bindables.BindingFragment
import com.zheng.lib.base.viewmodel.BaseViewModel
import com.zheng.lib.data.model.Resource
import com.zheng.lib.utils.observe
import com.zheng.lib.utils.viewModelsByVM


abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel> constructor(
    @LayoutRes private val contentLayoutId: Int
) : BindingFragment<T>(contentLayoutId) {

    private lateinit var mContext: Context
    private lateinit var mActivity: FragmentActivity

    @Suppress("UNCHECKED_CAST")
    val mViewModel: VM by viewModelsByVM()

    private var isFirstVisible = true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = requireActivity()
        mContext = requireContext()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialization()
        setupListener()
        observeViewModel()
        isFirstVisible = true
    }

    override fun onResume() {
        super.onResume()
        onVisible(isFirstVisible)
        if (isFirstVisible) {
            isFirstVisible = false
        }
    }

    override fun onPause() {
        super.onPause()
        onInvisible()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    //初始化方法
    abstract fun initialization()

    /**
     * 事情监听器
     */
    protected open fun setupListener() {

    }

    /**
     * 显示
     *
     * @param isFirst 是否第一次显示
     */
    protected open fun onVisible(isFirst: Boolean) {

    }

    /**
     * 隐藏
     */
    protected open fun onInvisible() {

    }

    protected open fun observeViewModel() {
        observe(mViewModel.dataLiveData, ::handleData)
    }

    open fun handleData(resource: Resource<*>) {

    }

    /**
     * 注册单个消息事件，消息对象:[Message]
     *
     * @param observer
     */
    protected open fun registerSingleLiveEvent(observer: Observer<Message>) {
        mViewModel.getSingleLiveEvent().observe(this, observer)
    }

}