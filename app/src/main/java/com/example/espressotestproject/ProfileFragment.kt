package com.example.espressotestproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    lateinit var tvUsername: AppCompatTextView
    lateinit var btnLogout: AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

        var username = "Hi, " + (activity as HomeActivity).getUserName()
        tvUsername.text = username

    }

    private fun initView(view: View) {
        tvUsername = view.findViewById(R.id.tv_user)
        btnLogout = view.findViewById(R.id.btn_log_out)

        btnLogout.setOnClickListener {
            SharePrefManager.clearUsername()
            val intent = Intent(activity, LoginPageActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}