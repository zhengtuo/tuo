package com.zheng.learn_android.ui.learn.view

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class GetDataFromActivity : ActivityResultContract<Void, String>() {
    override fun createIntent(context: Context, input: Void?): Intent {
        return Intent(context, GetResultActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (resultCode == ResultApiActivity.result_code) {
            if (intent != null) {
                return intent.getStringExtra("data")
            }
        }
        return null
    }

}