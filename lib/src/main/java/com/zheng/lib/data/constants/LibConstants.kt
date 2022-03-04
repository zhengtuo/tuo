package com.zheng.lib.data.constants

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class LibConstants {
    //object修饰的类是Singleton（单例）
    //在类的内部object 前加上 companion， 可以做成属于这个类的Singleton。
    companion object {
        const val MIN_API = 19

        const val DEFAULT_DATABASE_NAME = "room.db" //数据库名称

        const val PATH_CACHE = "cache_path"
        const val CACHE_SIZE = (1024 * 1024 * 50).toLong()

        const val DEFAULT_ROOM_DATABASE_MAX_SIZE = 60
        const val DEFAULT_RETROFIT_SERVICE_MAX_SIZE = 60
    }
}
