package com.mingtao.professionedu.ui.compose.login.view

import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mingtao.professionedu.R
import com.mingtao.professionedu.ui.compose.login.viewmodel.LoginVM
import com.mingtao.professionedu.ui.compose.theme.color_47A3FF
import com.mingtao.professionedu.ui.compose.theme.color_f


@Composable
fun LoginPage(vm: LoginVM) { //垂直排列元素
    Column(Modifier.background(color_f).fillMaxSize()) {
        Box(Modifier.fillMaxWidth()) {
            Image(painterResource(R.mipmap.ic_back_arrow), "返回", Modifier.width(41.dp).height(49.dp).padding(15.dp))
            Box(Modifier.size(43.dp).align(Alignment.TopEnd)) {
                Text(stringResource(R.string.skip), fontSize = 13.sp, color = color_47A3FF)
            }
        }
        Spacer(Modifier.height(44.dp))
        Image(painterResource(R.mipmap.login_logo), "logo", Modifier.align(Alignment.CenterHorizontally))
        Box(Modifier.fillMaxWidth().padding(horizontal = 30.dp)) {
            TextField(value = vm.phoneNumber, onValueChange = { vm.phoneNumber = it }, placeholder = { Text("手机号码") }, modifier = Modifier.fillMaxWidth())
        }

    }
}