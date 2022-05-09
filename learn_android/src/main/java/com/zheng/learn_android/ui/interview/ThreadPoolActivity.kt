package com.zheng.learn_android.ui.interview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zheng.base.utils.launch
import com.zheng.learn_android.R
import kotlinx.coroutines.sync.Semaphore
import java.lang.Exception
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ThreadPoolActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.la_activity_thread_pool)
        semaphoreUse()
    }

    private fun semaphoreUse() {
        //permits 初始许可数，也就是最大访问线程数
        val semaphore = Semaphore(permits = 5)
        for (index in 1..50) {
            Thread {
                launch({
                    //获取一把锁
                    semaphore.acquire()
                    println("ThreadName:${Thread.currentThread().name}开始运行")
                    try {
                        Thread.sleep(1000)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    println("ThreadName:${Thread.currentThread().name}结束运行")
                    //释放一把锁
                    semaphore.release()
                })

            }.start()

        }
    }

    private fun executorUse() {
        //缓存线程池
        Executors.newCachedThreadPool()
        //固定线程个数的线程池
        Executors.newFixedThreadPool(5)
        //计划任务线程池
        Executors.newScheduledThreadPool(5)
        //单个线程的线程池
        Executors.newSingleThreadExecutor()
    }

}