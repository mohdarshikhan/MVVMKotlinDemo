package com.mak.mvvmkotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mak.mvvmkotlindemo.common.activity.BaseActivity
import com.mak.mvvmkotlindemo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val isFullScreen: Boolean
        get() = false

    override val viewBinding: ActivityMainBinding
        get() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}