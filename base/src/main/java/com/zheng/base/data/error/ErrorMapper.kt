package com.zheng.base.data.error


//import com.zheng.lib.R
//import com.zheng.lib.data.error.Error
//import com.zheng.lib.data.error.ErrorMapperInterface
//import com.zheng.lib.utils.LibUtils
//import javax.inject.Inject
//
//open class ErrorMapper @Inject constructor() : ErrorMapperInterface {
//
//    override fun getErrorString(errorId: Int): String {
//        return LibUtils.getStringById(errorId)
//    }
//
//    override val errorsMap: HashMap<Int, String>
//        get() = hashMapOf(
//            Pair(Error.NO_INTERNET_CONNECTION, getErrorString(R.string.error_no_internet)),
//            Pair(Error.UN_KNOW, getErrorString(R.string.error_un_know)),
//            Pair(Error.EMPTY, getErrorString(R.string.error_empty)),
//        )
//}