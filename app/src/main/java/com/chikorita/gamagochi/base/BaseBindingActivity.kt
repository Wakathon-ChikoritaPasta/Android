package com.chikorita.gamagochi.base

import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseBindingActivity<T: ViewDataBinding>(@LayoutRes private val layoutId: Int): AppCompatActivity() {
    protected lateinit var binding: T
    private lateinit var callback: OnBackPressedCallback


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = DataBindingUtil.setContentView(this,layoutId)

        initView()
    }

    protected open fun initView() {}
    protected open fun initViewModel() {}
    protected open fun initListener() {}
    protected open fun afterViewCreated() {}

}