package com.chikorita.gamagochi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.chikorita.gamagochi.R
import com.chikorita.gamagochi.base.BaseActivity
import com.chikorita.gamagochi.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate){
    var inputIsValid = false

    override fun initView() {
        initListener()
    }
    private fun initListener(){
        binding.inputEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty() ){
                    inputIsValid = true
                }else{
                    inputIsValid = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.nextBtn.setOnClickListener{
            if(inputIsValid) {
                val intent = Intent(this, Register2Activity::class.java)
                startActivity(intent)
                overridePendingTransition(0, 0);

            }

        }

    }
}