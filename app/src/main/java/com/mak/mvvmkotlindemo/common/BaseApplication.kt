package com.mak.mvvmkotlindemo.common

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.mak.mvvmkotlindemo.data.network.AppApi
import com.mak.mvvmkotlindemo.data.network.interceptors.AuthInterceptor
import com.mak.mvvmkotlindemo.data.network.interceptors.NetworkConnectionInterceptor
import com.mak.mvvmkotlindemo.data.preferences.PreferenceProvider
import com.mak.mvvmkotlindemo.data.repositories.BookRepository
import com.mak.mvvmkotlindemo.ui.book.BooksViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class BaseApplication: MultiDexApplication(), KodeinAware, LifecycleObserver {

    companion object {
        var isBackgrounded = true
    }

    private var mContext: Context? = null

    fun getContext(): Context? {
        return mContext
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        mContext = base
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@BaseApplication))

        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { AuthInterceptor(instance()) }
        bind() from singleton { AppApi(instance()) }
//        bind() from singleton { AppApi(instance(), instance()) }

        // Repositories
        bind() from singleton { BookRepository(instance(), instance()) }

        // ViewModel Factories
        bind() from provider { BooksViewModelFactory(instance()) }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        isBackgrounded = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        isBackgrounded = false
    }
}