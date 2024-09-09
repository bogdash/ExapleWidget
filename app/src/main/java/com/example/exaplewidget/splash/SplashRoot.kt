package com.example.exaplewidget.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.glance.layout.Column

@Preview(showBackground = true)
@Composable
fun SplashRoot() {
    Column() {
        Text(text = "Weather")
    }
}