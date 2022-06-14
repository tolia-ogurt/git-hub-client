package com.ua.githubclient

import android.app.Application
import com.ua.githubclient.di.AppComponent
import com.ua.githubclient.di.DaggerAppComponent

class MyApplication : Application() {

    val appComponent: AppComponent by lazy { DaggerAppComponent.factory().create(applicationContext) }

}
