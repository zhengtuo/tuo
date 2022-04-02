package com.mingtao.professionedu.ui.compose.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mingtao.professionedu.R
import com.mingtao.professionedu.ui.compose.login.viewmodel.MTPCFindPasswordVM
import com.mingtao.professionedu.ui.compose.theme.*


@Composable
fun FindPasswordPage(vm: MTPCFindPasswordVM) { //垂直排列元素
    Column(Modifier.background(color_f).fillMaxSize()) {
        Box(Modifier.fillMaxWidth()) {
            Image(painterResource(R.mipmap.mtp_back_arrow), "返回", Modifier.width(41.dp).height(49.dp).padding(15.dp).noClickable {
                vm.finish()
            })
        }
        Spacer(Modifier.height(44.dp))
        Image(painterResource(R.mipmap.mtp_login_logo), "logo", Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(50.dp))
        Box(Modifier.fillMaxWidth().padding(horizontal = 30.dp)) {
            BasicTextField(value = vm.phoneNumber, onValueChange = {
                if (it.length <= 11) {
                    vm.phoneNumber = it
                }
            }, textStyle = TextStyle(fontSize = 15.sp), cursorBrush = SolidColor(Color.Gray), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), decorationBox = { innerTextField ->
                Box(Modifier.fillMaxWidth().drawWithContent {
                    drawContent()
                    drawLine(color_e, Offset(0F, size.height + 5.dp.toPx()), Offset(size.width, size.height + 5.dp.toPx()), 1.dp.toPx() / 2)
                }) {
                    if (vm.phoneNumber.isEmpty()) {
                        Text(text = stringResource(R.string.mtp_place_input_phone), fontSize = 15.sp, color = Color.Gray)
                    } else {
                        Image(painterResource(id = R.mipmap.mtp_login_text_clear), contentDescription = "清除手机号码", modifier = Modifier.align(Alignment.CenterEnd).clickable {
                            vm.phoneNumber = ""
                        })
                    }
                    innerTextField()
                }
            })
        }
        Spacer(Modifier.height(35.dp))
        Box(Modifier.fillMaxWidth().padding(horizontal = 30.dp)) {
            BasicTextField(value = vm.code, onValueChange = {
                if (it.length <= 6) {
                    vm.code = it
                }
            }, textStyle = TextStyle(color = Color.Black, fontSize = 15.sp), singleLine = true, cursorBrush = SolidColor(Color.Gray), decorationBox = { innerTextField ->
                Box(Modifier.fillMaxWidth().drawWithContent {
                    drawContent()
                    drawLine(color_e, Offset(0F, size.height + 5.dp.toPx()), Offset(size.width, size.height + 5.dp.toPx()), 1.dp.toPx() / 2)
                }) {
                    if (vm.code.isEmpty()) {
                        Text(text = stringResource(R.string.mtp_place_input_code), color = Color.Gray, fontSize = 15.sp)
                    }
                    Text(vm.time, Modifier.align(Alignment.CenterEnd).noClickable { vm.sendCode() }, if (vm.canSendCode()) color_47A3FF else color_c)
                    innerTextField()
                }
            })
        }
        Spacer(Modifier.height(35.dp))
        Box(Modifier.fillMaxWidth().padding(horizontal = 30.dp)) {
            BasicTextField(value = vm.password, onValueChange = {
                if (it.length <= 16) {
                    vm.password = it
                }
            }, textStyle = TextStyle(color = Color.Black, fontSize = 15.sp), singleLine = true, cursorBrush = SolidColor(Color.Gray), visualTransformation = if (vm.isCiphertext) PasswordVisualTransformation() else VisualTransformation.None, decorationBox = { innerTextField ->
                Box(Modifier.fillMaxWidth().drawWithContent {
                    drawContent()
                    drawLine(color_e, Offset(0F, size.height + 5.dp.toPx()), Offset(size.width, size.height + 5.dp.toPx()), 1.dp.toPx() / 2)
                }) {
                    if (vm.password.isEmpty()) {
                        Text("请输入新密码（6~16位字母或数字）", color = Color.Gray, fontSize = 15.sp)
                    }
                    Image(painterResource(if (vm.isCiphertext) R.mipmap.mtp_eyes else R.mipmap.mtp_eyes_pre), contentDescription = "清除手机号码", modifier = Modifier.align(Alignment.CenterEnd).clickable {
                        vm.isCiphertext = !vm.isCiphertext
                    })
                    innerTextField()
                }
            })
        }
        Spacer(Modifier.height(35.dp))
        Box(Modifier.padding(horizontal = 30.dp).noClickable {
            //点击修改
            vm.changePassword()
        }) {
            Box(Modifier.fillMaxWidth().background(if (vm.canChange()) color_47A3FF else color_F7F7F7, RoundedCornerShape(5.dp)).padding(vertical = 12.dp)) {
                Text(stringResource(R.string.mtp_change_password), Modifier.align(Alignment.Center), color = if (vm.canChange()) color_f else color_b, fontSize = 14.sp)
            }
        }
    }
}