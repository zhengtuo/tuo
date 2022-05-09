package app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.zheng.base.delegate.ApplicationDelegate
import com.zheng.comon.utils.CommonUtils
import com.zheng.learn_android.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
open class LKApp : Application() {


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        initApp()
    }

    /**
     * 初始化app相关
     */
    private fun initApp() {
        CommonUtils.context = this
        //LibUtils.setNetConstants(NetConfig().setBaseUrl("http://php.mingtaoedu.com/mt/public/index.php/").setInterceptor(HeadInterceptor()))
        Timber.plant(Timber.DebugTree())
        initARouter()
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {                         // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()                            // 打印日志
            ARouter.openDebug()                          // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
    }
}