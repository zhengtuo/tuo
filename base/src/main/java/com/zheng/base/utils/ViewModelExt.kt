package com.zheng.base.utils

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.*
import java.lang.reflect.ParameterizedType


//ViewModel的范围 防止内存泄漏
fun ViewModel.launch(block: suspend CoroutineScope.() -> Unit, onError: (e: Throwable) -> Unit = {}, onComplete: () -> Unit = {}): Job {
    return viewModelScope.launch(CoroutineExceptionHandler { _, e -> onError(e) }) {
        try {
            block.invoke(this)
        } finally {
            onComplete()
        }
    }
}

fun launch(block: suspend CoroutineScope.() -> Unit, onError: (e: Throwable) -> Unit = {}, onComplete: () -> Unit = {}): Job {
    return GlobalScope.launch(CoroutineExceptionHandler { _, e -> onError(e) }) {
        try {
            Dispatchers.Unconfined
            block.invoke(this)
        } finally {
            onComplete()
        }
    }
}

//修改viewModels方法 根据泛型获取ViewModel实例
@Suppress("UNCHECKED_CAST")
@MainThread
fun <VM : ViewModel> ComponentActivity.viewModelsByVM(factoryProducer: (() -> ViewModelProvider.Factory)? = null): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }
    val type = javaClass.genericSuperclass as ParameterizedType
    val classVM = type.actualTypeArguments[1] as Class<VM>
    return ViewModelLazy(classVM.kotlin, { viewModelStore }, factoryPromise)
}

//修改viewModels方法 根据泛型获取ViewModel实例
@Suppress("UNCHECKED_CAST")
@MainThread
fun <VM : ViewModel> ComponentActivity.viewModelsComposeBy(factoryProducer: (() -> ViewModelProvider.Factory)? = null): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }
    val type = javaClass.genericSuperclass as ParameterizedType
    val classVM = type.actualTypeArguments[0] as Class<VM>
    return ViewModelLazy(classVM.kotlin, { viewModelStore }, factoryPromise)
}

//修改viewModels方法 根据泛型获取ViewModel实例
@Suppress("UNCHECKED_CAST")
@MainThread
fun <VM : ViewModel> Fragment.viewModelsByVM(factoryProducer: (() -> ViewModelProvider.Factory)? = null): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }
    val type = javaClass.genericSuperclass as ParameterizedType
    val classVM = type.actualTypeArguments[1] as Class<VM>
    return ViewModelLazy(classVM.kotlin, { viewModelStore }, factoryPromise)
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer {
        it?.let { t ->
            action(t)
        }
    })
}


