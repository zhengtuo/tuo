package com.mingtao.professionedu.base.activity

import com.mingtao.professionedu.lib.gloading.Gloading
import com.zheng.base.activity.BaseComposeActivity
import com.zheng.base.viewmodel.BaseViewModel


abstract class BaseMTPCActivity<VM : BaseViewModel> : BaseComposeActivity<VM>() {

    private var mHolder: Gloading.Holder? = null

    /**
     * make a Gloading.Holder wrap with current activity by default
     * override this method in subclass to do special initialization
     */
    protected open fun initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(this).withRetry { onLoadRetry() }
        }
    }

    protected open fun onLoadRetry() {
        // override this method in subclass to do retry task
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

}