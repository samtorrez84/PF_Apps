package com.example.pf1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pf1.ui.theme.AppNavigation
import com.example.pf1.ui.theme.RecycleClassifierAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecycleClassifierAppTheme {
                AppNavigation()
            }
        }
    }
}
