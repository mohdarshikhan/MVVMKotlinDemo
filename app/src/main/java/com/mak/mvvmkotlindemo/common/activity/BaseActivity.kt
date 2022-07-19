package com.mak.mvvmkotlindemo.common.activity

import android.app.AlertDialog
import androidx.viewbinding.ViewBinding
import androidx.appcompat.app.AppCompatActivity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import com.google.android.material.snackbar.Snackbar
import android.widget.Toast
import kotlin.jvm.JvmOverloads
import com.mak.mvvmkotlindemo.R
import android.content.DialogInterface
import android.text.TextUtils
import android.view.Window
import com.mak.mvvmkotlindemo.common.activity.BaseActivity

/**
 * This is the base activity which will include the basic functionality of every sub activity. Every activity must
 * be inherited from this.
 *
 *
 * Copyright (c) 2018 <ClientName>. All rights reserved.
 * Created by mak on 20/9/18.
</ClientName> */
abstract class BaseActivity<V : ViewBinding?> : AppCompatActivity() {
    @JvmField
    protected var binding: V? = null
    private var mDialog: ProgressDialog? = null
    protected abstract val isFullScreen: Boolean
    protected abstract val viewBinding: V?
    val context: Context
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        if (viewBinding != null) {
            binding = viewBinding
            val view = binding!!.root
            setContentView(view)
        }
        resetTitles()
    }

    private fun resetTitles() {
        try {
            val info = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
            if (info.labelRes != 0) {
                setTitle(info.labelRes)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    fun showSnackBar(message: String?) {
        Snackbar.make(findViewById(android.R.id.content), message!!, Snackbar.LENGTH_LONG).show()
    }

    fun showToast(message: String?, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }

    @JvmOverloads
    fun showAlert(
        message: String?,
        posBtnLabel: String? = getString(R.string.lbl_ok),
        negBtnLabel: String? = null,
        onClickListener: DialogInterface.OnClickListener? =
            DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int -> }
    ) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton(posBtnLabel, onClickListener)
        if (!TextUtils.isEmpty(negBtnLabel)) {
            builder.setNegativeButton(negBtnLabel, onClickListener)
        }
        builder.show()
    }

    fun showProgressDialog() {
        if (mDialog != null && mDialog!!.isShowing) {
            return
        }
        mDialog = ProgressDialog(context)
        mDialog!!.setCancelable(false)
        mDialog!!.setCanceledOnTouchOutside(false)
        mDialog!!.show()
    }

    fun hideProgressDialog() {
        if (mDialog != null && mDialog!!.isShowing) {
            mDialog!!.dismiss()
        }
    }

    companion object {
        private val TAG = BaseActivity::class.java.name
    }
}