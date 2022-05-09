package com.mingtao.professionedu.ui.compose.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alibaba.android.arouter.launcher.ARouter
import com.mingtao.professionedu.R
import com.mingtao.professionedu.ui.compose.login.viewmodel.MTPCLoginVM
import com.mingtao.professionedu.ui.compose.theme.*
import com.zheng.comon.arouter.RouterPath


@Composable
fun LoginPage(vm: MTPCLoginVM) { //垂直排列元素
    Column(Modifier.background(color_f).fillMaxSize()) {
        Box(Modifier.fillMaxWidth()) {
            if (vm.showJump) {
                Box(Modifier.size(43.dp).align(Alignment.TopEnd).noClickable {
                    vm.goMain()
                }, contentAlignment = Alignment.Center) {
                    Text(stringResource(R.string.mtp_skip), fontSize = 13.sp, color = color_47A3FF)
                }
            } else {
                Image(painterResource(R.mipmap.mtp_back_arrow), "返回", Modifier.width(41.dp).height(49.dp).padding(15.dp).noClickable {
                    vm.finish()
                })
            }


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
        Spacer(Modifier.height(43.dp))
        Box(Modifier.padding(horizontal = 30.dp).noClickable {
            //点击登录/注册
            vm.loginOrRegister()
        }) {
            Box(Modifier.fillMaxWidth().background(if (vm.canLoginOrRegister()) color_47A3FF else color_F7F7F7, RoundedCornerShape(5.dp)).padding(vertical = 12.dp)) {
                Text(stringResource(R.string.mtp_login_and_register), Modifier.align(Alignment.Center), color = if (vm.canLoginOrRegister()) color_f else color_b, fontSize = 14.sp)
            }
        }
        Box(Modifier.padding(top = 15.dp, bottom = 15.dp, end = 30.dp).align(Alignment.End).noClickable {
            ARouter.getInstance().build(RouterPath.MTP_PATH_LOGIN_PASSWORD).navigation()
        }) {
            Text(stringResource(R.string.mtp_password_login), Modifier.align(Alignment.Center), color = color_47A3FF, fontSize = 13.sp)
        }
        Spacer(Modifier.height(40.dp))
        Text("第三方账号快捷登录", Modifier.align(Alignment.CenterHorizontally), color_b, 12.sp)
        Spacer(Modifier.height(20.dp))
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Image(painterResource(R.mipmap.mtp_qq_login), "QQ登录", Modifier.size(36.dp))
            Spacer(Modifier.width(28.dp))
            Image(painterResource(R.mipmap.mtp_wechat_login), "微信登录", Modifier.size(36.dp))
        }
        Spacer(Modifier.weight(1.0F))
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.background(Color.Transparent).noClickable { vm.checked = !vm.checked }) {
                Image(painterResource(if (vm.checked) R.mipmap.mtp_login_select else R.mipmap.mtp_login_no_select), contentDescription = "协议", Modifier.size(12.dp).align(Alignment.CenterVertically))
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

//没有点击特性的点击事件
fun Modifier.noClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(onClick = onClick, indication = null, interactionSource = remember {
        MutableInteractionSource()
    })
}

