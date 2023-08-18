package com.chikorita.gamagochi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chikorita.gamagochi.R
import com.chikorita.gamagochi.base.BaseActivity
import com.chikorita.gamagochi.databinding.ActivityOnBoardingBinding
import com.chikorita.gamagochi.databinding.ActivitySplashBinding

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>(
    ActivityOnBoardingBinding::inflate){
    override fun initView() {
        initListener()
    }
    private fun initListener(){

        binding.nextBtn.setOnClickListener{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)


        }

    }
}