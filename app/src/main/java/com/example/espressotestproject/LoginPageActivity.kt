package com.example.espressotestproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView

class LoginPageActivity : AppCompatActivity() {

    lateinit var etUsername: AppCompatEditText
    lateinit var etPassword: AppCompatEditText
    lateinit var tvErrorText: AppCompatTextView
    lateinit var btnRegister: AppCompatButton
    lateinit var btnSignIn: AppCompatButton

    var sharePrefManager = SharePrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        initView()
        checkIfUserExist()
    }

    private fun initView() {
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        tvErrorText = findViewById(R.id.tv_error_text)
        btnRegister = findViewById(R.id.btn_register)
        btnSignIn = findViewById(R.id.btn_sign_in)

        btnSignIn.setOnClickListener{
            checkValue()
        }

        btnRegister.setOnClickListener{
            goToRegisterActivity()
        }
    }

    private fun checkValue(){
        if (etUsername.text.toString() == "alfred" && etPassword.text.toString() == "password")
        {
            goToHomeActivity(etUsername.text.toString())
        }
        else
        {
            showErrorText(true)
        }
    }

    private fun checkIfUserExist() {
        sharePrefManager.setUpPref(this)
        var username = sharePrefManager.getUsername()
        if (username != "") {
            goToHomeActivity(username)
        }
    }

    private fun goToHomeActivity(username: String){
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("username", username)
        startActivity(intent)
    }

    private fun goToRegisterActivity(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun showErrorText(show: Boolean) {
        if (show) {
            tvErrorText.visibility = View.VISIBLE
        }
        else
            tvErrorText.visibility = View.GONE
    }
}