package com.chikorita.gamagochi.view

import android.content.Intent
import android.content.res.ColorStateList
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContextCompat
import com.chikorita.gamagochi.R
import com.chikorita.gamagochi.base.ApplicationClass.Companion.USER_ID
import com.chikorita.gamagochi.base.BaseActivity
import com.chikorita.gamagochi.data.register.RegisterService
import com.chikorita.gamagochi.data.register.RegisterView
import com.chikorita.gamagochi.data.register.SetUserInfoResponse
import com.chikorita.gamagochi.databinding.ActivityRegister2Binding
import com.chikorita.gamagochi.base.ApplicationClass.Companion.sSharedPreferences


class Register2Activity : BaseActivity<ActivityRegister2Binding>(ActivityRegister2Binding::inflate), RegisterView, AdapterView.OnItemSelectedListener{
    var inputIsValid = true
    lateinit var items : Array<String>
    lateinit var spinner : Spinner

    lateinit var username : String

    override fun initView() {
        initListener()
        response()
        spinnerAdapter()

        username = intent.getStringExtra("username").toString()

    }
    private fun initListener(){

        val userId = sSharedPreferences.getInt(USER_ID, 1351345461)
        binding.nextBtn.setOnClickListener{

            RegisterService(this).trySetUserInfo(userId, username, binding.inputEt.selectedItem.toString())

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

    override fun onSetUserInfoSuccess(response: SetUserInfoResponse) {
        Log.d("SetUserInfo", response.toString())
    }

    override fun onSetUserInfoFailure(message: String) {
        Log.d("SetUserInfo(failure)", message)
    }
}