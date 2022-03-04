package com.zheng.tuo.ui.learn.view

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.zheng.lib.base.activity.BaseActivity
import com.zheng.lib.base.viewmodel.EmptyViewModel
import com.zheng.lib.databinding.ActivityNoBinding
import com.zheng.tuo.R
import com.zheng.tuo.databinding.ActivityStorageBinding
import timber.log.Timber
import java.io.*

class StorageActivity : BaseActivity<ActivityStorageBinding, EmptyViewModel>(R.layout.activity_storage) {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun initialization() {

        initStorage()
        //外部存储
        initOutStorage()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initStorage() {
        //存储划分 Android 4.4之前 Android 4.4之后

        //内部存储 存放位置 1.SharedPreferences 适用于存储小文件 2.数据库 存储结构比较复杂的大文件

        //存储内容 cache 存放缓存文件 code_cache 存放运行时代码优化等产生的缓存 databases 存放数据库文件
        //files 存放一般文件 shared_prefs 存放SharedPreferences文件 lib 存放App依赖的so库


        val path = File(mContext.filesDir, "myFile.txt").absolutePath

        //访问方式 字符流和字节流
        writeFile(path)
        readFile(path)
        //读写files目录下文件
        getFilePath()
        //读写cache目录下文件
        getCachePath()
        //读写shared_prefs目录下文件
        getSp("fileName", "key", "value")
        //读写数据库目录下文件
        getDbPath()
        //读写code_cache目录下文件
        getCodeCachePath()

        getImageForMedia("result_api_1.png")
    }

    //写入文件
    private fun writeFile(filePath: String) {
        try {
            val file = File(filePath)
            val fileOutputStream = FileOutputStream(file)

            /**
             * 操作模式
             * Context.MODE_PRIVATE    = 0         为默认操作模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容
             * Context.MODE_APPEND    =  32768     模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件
             * Context.MODE_WORLD_READABLE =  1    表示当前文件可以被其他应用读取
             * Context.MODE_WORLD_WRITEABLE =  2   表示当前文件可以被其他应用写入
             */
            //val fileOutputStream = mContext.openFileOutput(filePath, Context.MODE_PRIVATE)
            val bos = BufferedOutputStream(fileOutputStream)
            val writeContent = "hello world\n"
            bos.write(writeContent.toByteArray())
            bos.flush()
            bos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //从文件读取
    private fun readFile(filePath: String) {
        try {
            val file = File(filePath)
            //val fileInputStream = mContext.openFileInput(filePath)
            val fileInputStream = FileInputStream(file)
            val bis = BufferedInputStream(fileInputStream)
            val readContent = ByteArray(1024)
            var readLen = 0
            while (readLen != -1) {
                readLen = bis.read(readContent, 0, readContent.size)
                if (readLen > 0) {
                    val content = String(readContent)
                    Timber.d("read content:%s", content.substring(0, readLen))
                }
            }
            fileInputStream.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getFilePath(): String {
        //获取files根目录
        val fileDir = mContext.filesDir
        //获取文件
        val myFile = File(fileDir, "myFile")
        return myFile.absolutePath
    }

    private fun getCachePath(): String {
        //获取files根目录
        val fileDir = mContext.cacheDir
        //获取文件
        val myFile = File(fileDir, "myFile")
        return myFile.absolutePath
    }

    private fun getSp(fileName: String, key: String, value: String) {
        //构造SP文件
        val sp = getSharedPreferences(fileName, MODE_PRIVATE)
        //写入SP
        sp.edit().putString(key, value).apply()
        //读取SP
        val myValue = sp.getString(key, "")
    }

    private fun getDbPath() {
        //MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(v.getContext(), "myDB", null, 10);
        val db = mContext.getDatabasePath("myDB")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getCodeCachePath() {
        val cachePath = mContext.codeCacheDir
        //如果想获取：/data/user/0/com.zheng.tuo/ 可通过：
        val path = mContext.dataDir
    }

    private fun initOutStorage() {
        /**
         * 存放位置 存储的根目录是："/"  /data/ /sdcard/ /storage/
         * /sdcard/是软链接,指向/storage/self/primary
         * /storage/self/primary/是软链接，指向/storage/emulated/0/
         * 也就是说/sdcard/、/storage/self/primary/ 真正指向的是/storage/emulated/0/
         * 共享存储空间 也就是所有App共享的部分，比如相册、音乐、铃声、文档等。
         * 1.媒体文件 2.文档和其他文件
         */
    }

    @SuppressLint("Range")
    private fun getImageForMedia(imageName: String): Uri? {
        val contentResolver = contentResolver
        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media.DISPLAY_NAME + "= '$imageName'", null, null)
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    val imgId = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID))
                    val uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imgId.toString())
                    Timber.d("StorageActivity%s", imgId)
                    return uri
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            cursor.close()
        }
        return null
    }
}