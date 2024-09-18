package com.example.myweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.design.theme.MyWeatherTheme
import com.example.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWeatherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MyWeatherTheme.colors.background,
                ) {
                    Navigation()
                }
            }
        }
    }
}
