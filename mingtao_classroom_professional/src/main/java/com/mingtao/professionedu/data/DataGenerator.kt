package com.mingtao.professionedu.data

//import androidx.room.Room
//import androidx.room.RoomDatabase
import androidx.collection.LruCache
import com.zheng.comon.constants.ComonConstants
import dagger.Lazy
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/20 18:29
 */
@Module
@InstallIn(SingletonComponent::class)
class DataGenerator @Inject constructor() {

    @Inject
    @Singleton
    lateinit var mRetrofit: Lazy<Retrofit>

    lateinit var mBaseServiceCache: LruCache<String, Any>

    lateinit var mRoomDatabaseCache: LruCache<String, Any>

    /**
     * 传入Class 通过[create(Class)][retrofit2.Retrofit] 获得对应的Class
     *
     * @param service
     * @param <T>
     * @return [create(Class)][retrofit2.Retrofit]
    </T> */
    @Suppress("UNCHECKED_CAST")
    fun <T> getRetrofitService(service: Class<T>): T {

        if (!this::mBaseServiceCache.isInitialized) {
            mBaseServiceCache = LruCache(ComonConstants.DEFAULT_RETROFIT_SERVICE_MAX_SIZE)
        }
        var retrofitService: T = service.canonicalName?.let { mBaseServiceCache.get(it) } as T

        if (retrofitService == null) {
            synchronized(mBaseServiceCache) {
                if (retrofitService == null) {
                    retrofitService = mRetrofit.get().create(service)
                    //缓存
                    service.canonicalName?.let {
                        mBaseServiceCache.put(it, retrofitService!!)
                    }
                }
            }
        }
        return retrofitService
    }


    /**
     * 传入Class 通过[Room.databaseBuilder],[#build()][<]获得对应的Class
     *
     * @param database
     * @param dbName   为`null`时，默认为[ApiConstants.DEFAULT_DATABASE_NAME]
     * @param <T>
     * @return [#build()][<]
    </T> */
//    @Suppress("UNCHECKED_CAST")
//    fun <T : RoomDatabase?> getRoomDatabase(database: Class<T>, dbName: String): T {
//        if (!this::mRoomDatabaseCache.isInitialized) {
//            mRoomDatabaseCache = LruCache(ComonConstants.DEFAULT_ROOM_DATABASE_MAX_SIZE)
//        }
//        var roomDatabase: T = database.canonicalName?.let { mRoomDatabaseCache.get(it) } as T
//
//        if (roomDatabase == null) {
//            synchronized(mRoomDatabaseCache) {
//                if (roomDatabase == null) {
//                    val builder = Room.databaseBuilder(
//                        CommonUtils.getLibContext(),
//                        database,
//                        if (TextUtils.isEmpty(dbName)) LibConstants.DEFAULT_DATABASE_NAME else dbName
//                    )
//                    roomDatabase = builder
//                        .fallbackToDestructiveMigration()
//                        .allowMainThreadQueries().build()
//                    //缓存
//                    mRoomDatabaseCache.put(database.canonicalName!!, roomDatabase!!)
//                }
//            }
//        }
//        return roomDatabase
//    }
}