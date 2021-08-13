package com.appnio.androidhottopics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.appnio.androidhottopics.ui.theme.AndroidHotTopicsTheme

/**
 * Designer credit
 * https://dribbble.com/shots/10514903--Freebie-Finance-Mobile-Application-Exploration?utm_source=Clipboard_Shot&utm_campaign=Kevinduk&utm_content=%E2%AC%87%EF%B8%8F%20Freebie%20%7C%20Finance%20Mobile%20Application%20Exploration&utm_medium=Social_Share&utm_source=Clipboard_Shot&utm_campaign=Kevinduk&utm_content=%E2%AC%87%EF%B8%8F%20Freebie%20%7C%20Finance%20Mobile%20Application%20Exploration&utm_medium=Social_Share
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidHotTopicsTheme {
                Surface {
                    BankingApp()
                }
            }
        }
    }
}