package com.example.footballfixtures

import android.app.Application

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FootBallApplication: Application() {
    override fun onCreate() {
        super.onCreate()

    }
}