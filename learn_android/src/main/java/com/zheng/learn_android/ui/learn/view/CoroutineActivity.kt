package com.zheng.learn_android.ui.learn.view

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zheng.base.data.model.Resource
import com.zheng.learn_android.R
import kotlinx.coroutines.*

//协程 协程基于线程，它是轻量级线程(1.处理耗时任务2.保证主线程安全)
class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.la_activity_coroutine)
        val content = findViewById<TextView>(R.id.la_tv_content)
        val asyncTask = findViewById<Button>(R.id.la_bt_asyncTask).also {
            it.setOnClickListener {
                initAsyncTask(content)
            }
        }
        val coroutine = findViewById<Button>(R.id.la_bt_coroutine).also {
            it.setOnClickListener {
                initCoroutine(content)
            }

        }
    }

    @Suppress("DEPRECATION")
    @SuppressLint("StaticFieldLeak")
    //过时
    fun initAsyncTask(content: TextView) {
        object : AsyncTask<Void, Void, Resource<Any>>() {
            override fun doInBackground(vararg p0: Void?): Resource<Any> {
                return Resource.Success(data = "AsyncTask", methodName = null)
            }

            override fun onPostExecute(result: Resource<Any>?) {
                content.text = result?.data as String
            }
        }.execute()
    }

    //
    fun initCoroutine(content: TextView) {
        GlobalScope.launch(Dispatchers.Main) {
            val ct = withContext(Dispatchers.IO) {
                delay(2000)
                "Coroutine"
            }
            content.text = ct
        }
    }

    //协程最核心的点就是,函数或者一段程序能够被挂起,稍后再在挂起的位置恢复。
    //suspend - 也称为挂起或暂停,用于暂停执行当前协程,并保持所有局部变量
    //resume - 用于让已暂停的协程从其暂停处继续执行
    fun initCoroutine2(content: TextView) {
        GlobalScope.launch(Dispatchers.Main) {
            val value = withContext(Dispatchers.IO) {
                getContent()
            }
            setContent(content, value)
        }
    }

    //使用suspend关键字修饰的函数叫作挂起函数
    //挂起函数只能在协程体内或其他挂起函数内调用
    suspend fun getContent(): String {
        delay(1000)
        return "initCoroutine2"
    }

    fun setContent(content: TextView, value: String) {
        content.text = value
    }

}
