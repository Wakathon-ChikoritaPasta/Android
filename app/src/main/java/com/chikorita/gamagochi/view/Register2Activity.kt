package com.chikorita.gamagochi.view

import android.content.Intent
import android.content.res.ColorStateList
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContextCompat
import com.chikorita.gamagochi.R
import com.chikorita.gamagochi.base.BaseActivity
import com.chikorita.gamagochi.databinding.ActivityRegister2Binding


class Register2Activity : BaseActivity<ActivityRegister2Binding>(ActivityRegister2Binding::inflate), AdapterView.OnItemSelectedListener{
    var inputIsValid = true
    lateinit var items : Array<String>
    lateinit var spinner : Spinner

    override fun initView() {
        initListener()
        response()
        spinnerAdapter()
    }
    private fun initListener(){

        binding.nextBtn.setOnClickListener{
            if(inputIsValid) {
                val intent = Intent(this, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            }

        }



    }

    private fun spinnerAdapter(){
        val spinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items)
        spinner = binding.inputEt
        spinner.setAdapter(spinnerAdapter)

        spinner.setOnItemSelectedListener(this)
    }

    private fun response(){
        items = arrayOf("소프트웨어학과", "인공지능학과", "컴퓨터공학과", "스마트보안학과", "경영학과")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //spinner.text(items[p2]);
        changeButtonState(true)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //spinner.setText(items[0]);
        changeButtonState(false)
    }

    private fun changeButtonState(status: Boolean) {
        val btn = binding.nextBtn
        val btnColor = if (status) R.color.primary_default else R.color.primary_light

        // 버튼 상태 변경
        btn.isClickable = status
        btn.isEnabled = status
        btn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, btnColor))
    }
}