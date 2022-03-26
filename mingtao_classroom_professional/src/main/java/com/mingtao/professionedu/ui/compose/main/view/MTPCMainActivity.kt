package com.mingtao.professionedu.ui.compose.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.mingtao.professionedu.ui.compose.main.viewmodel.MainVM
import com.mingtao.professionedu.ui.compose.theme.ComposeMTPTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class MTPCMainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMTPTheme {
                Column {
                    //val viewModel: MainVM = viewModel()
                    HorizontalPager(count = 4,Modifier.weight(1F)) { page ->
                        when (page) {
                            0 -> HomePage(2)
                            1 -> Box(Modifier.fillMaxSize())
                            2 -> Box(Modifier.fillMaxSize())
                            3 -> Box(Modifier.fillMaxSize())
                        }
                    }
//                    BottomTab(viewModel.selectedTab) {
//                        viewModel.selectedTab = it
//                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomTabPreview() {
    var selectedTab by remember {
        mutableStateOf(0)
    }
    BottomTab(selectedTab) { selectedTab = it }
}