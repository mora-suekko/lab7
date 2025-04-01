package com.example.lab7

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogout = findViewById<Button>(R.id.btn_logout)
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        if (!sharedPref.getBoolean("isLoggedIn", false)) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        btnLogout.setOnClickListener {
            val editor = sharedPref.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}