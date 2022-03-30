package app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.mingtao.professionedu.BuildConfig
import com.mingtao.professionedu.data.remote.interceptor.HeadInterceptor
import com.mingtao.professionedu.lib.gloading.Gloading
import com.mingtao.professionedu.lib.gloading.GlobalAdapter
import com.zheng.base.delegate.ApplicationDelegate
import com.zheng.base.utils.BaseUtils
import com.zheng.comon.utils.CommonUtils
import com.zheng.network.config.RemoteConfig
import com.zheng.network.model.NetConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MTPApp : Application() {

    /**
     * Application 代理 规避项目中集成了其它第三方类或其它原因，不能集成本类BaseApplication时，
     * 参照本类实现 ApplicationDelegate 即可初始化MVVMFrame框架配置信息。
     */
    lateinit var mApplicationDelegate: ApplicationDelegate

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        mApplicationDelegate = ApplicationDelegate(this)
        mApplicationDelegate.attachBaseContext(base, BuildConfig.DEBUG)
    }

    override fun onCreate() {
        super.onCreate()
        mApplicationDelegate.onCreate()
        initApp()
    }

    /**
     * 初始化app相关
     */
    private fun initApp() {
        BaseUtils.context = this
        CommonUtils.init(this)
        initARouter()
        RemoteConfig.initConfig(NetConfig().setBaseUrl("https://api.mtskedu.com/").setInterceptor(HeadInterceptor()))
        Gloading.initDefault(GlobalAdapter())
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {                         // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()                            // 打印日志
            ARouter.openDebug()                          // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
    }
}