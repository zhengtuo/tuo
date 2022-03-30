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
import com.mingtao.professionedu.ui.compose.login.viewmodel.MTPCPasswordLoginVM
import com.mingtao.professionedu.ui.compose.theme.*


@Composable
fun PasswordLoginPage(vm: MTPCPasswordLoginVM) { //垂直排列元素
    Column(Modifier.background(color_f).fillMaxSize()) {
        Box(Modifier.fillMaxWidth()) {
            Image(painterResource(R.mipmap.ic_back_arrow), "返回", Modifier.width(41.dp).height(49.dp).padding(15.dp).noClickable {
                vm.finish()
            })
        }
        Spacer(Modifier.height(44.dp))
        Image(painterResource(R.mipmap.login_logo), "logo", Modifier.align(Alignment.CenterHorizontally))
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
                        Image(painterResource(id = R.mipmap.login_text_clear), contentDescription = "清除手机号码", modifier = Modifier.align(Alignment.CenterEnd).clickable {
                            vm.phoneNumber = ""
                        })
                    }
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
                        Text(text = stringResource(R.string.mtp_place_input_password), color = Color.Gray, fontSize = 15.sp)
                    }
                    Image(painterResource(if (vm.isCiphertext) R.mipmap.eyes else R.mipmap.eyes_pre), contentDescription = "清除手机号码", modifier = Modifier.align(Alignment.CenterEnd).clickable {
                        vm.isCiphertext = !vm.isCiphertext
                    })
                    innerTextField()
                }
            })
        }
        Spacer(Modifier.height(15.dp))
        Row(Modifier.padding(horizontal = 30.dp).noClickable {
            vm.rememberChecked = !vm.rememberChecked
        }) {
            Image(painterResource(if (vm.rememberChecked) R.mipmap.remember_password_select else R.mipmap.remember_password_unselect), contentDescription = "记住密码", Modifier.size(12.dp).align(Alignment.CenterVertically))
            Spacer(Modifier.width(5.dp))
            Text(stringResource(R.string.mtp_place_remember_password), color = color_b, fontSize = 11.sp)
        }
        Spacer(Modifier.height(28.dp))
        Box(Modifier.padding(horizontal = 30.dp).noClickable {
            //点击登录
            vm.login()
        }) {
            Box(Modifier.fillMaxWidth().background(if (vm.canLogin()) color_47A3FF else color_F7F7F7, RoundedCornerShape(5.dp)).padding(vertical = 12.dp)) {
                Text(stringResource(R.string.mtp_login), Modifier.align(Alignment.Center), color = if (vm.canLogin()) color_f else color_b, fontSize = 14.sp)
            }
        }
        Box(Modifier.padding(30.dp, 15.dp).fillMaxWidth()) {
            Text(stringResource(R.string.mtp_find_password), Modifier.align(Alignment.CenterStart).noClickable {
                //找回密码

            }, color = color_47A3FF, fontSize = 13.sp)
            Text(stringResource(R.string.mtp_code_login), Modifier.align(Alignment.CenterEnd).noClickable {
                vm.finish()
            }, color = color_47A3FF, fontSize = 13.sp)
        }

        Spacer(Modifier.weight(1.0F))
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.background(Color.Transparent).noClickable { vm.checked = !vm.checked }) {
                Image(painterResource(if (vm.checked) R.mipmap.login_select else R.mipmap.login_no_select), contentDescription = "协议", Modifier.size(12.dp).align(Alignment.CenterVertically))
                Spacer(Modifier.width(5.dp))
                Text("同意并愿意遵守名淘尚科", color = color_b, fontSize = 12.sp)
            }
            Spacer(Modifier.height(8.dp))
            Row {
                Text(stringResource(R.string.mtp_service_agreement), Modifier.noClickable {
                    //点击用户服务协议
                }, color_47A3FF, 12.sp)
                Text("、", color = color_b, fontSize = 12.sp)
                Text(stringResource(R.string.mtp_buy_agreement), Modifier.noClickable {
                    //点击用户付费协议
                }, color_47A3FF, 12.sp)
                Text("、", color = color_b, fontSize = 12.sp)
                Text(stringResource(R.string.mtp_conceal_agreement), Modifier.noClickable {
                    //点击用户隐私协议
                }, color_47A3FF, 12.sp)
            }
            Spacer(Modifier.height(28.dp))
        }
    }
}