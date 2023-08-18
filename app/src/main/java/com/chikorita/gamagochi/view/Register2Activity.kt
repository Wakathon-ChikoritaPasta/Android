package com.chikorita.gamagochi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.chikorita.gamagochi.R
import com.chikorita.gamagochi.base.BaseActivity
import com.chikorita.gamagochi.databinding.ActivityRegister2Binding
import com.chikorita.gamagochi.databinding.ActivityRegisterBinding

class Register2Activity : BaseActivity<ActivityRegister2Binding>(ActivityRegister2Binding::inflate){
    var inputIsValid = true

    override fun initView() {
        initListener()
    }
    private fun initListener(){

        binding.nextBtn.setOnClickListener{
            if(inputIsValid) {
                val intent = Intent(this, OnBoardingActivity::class.java)
                startActivity(intent)
            }

        }

    }
}