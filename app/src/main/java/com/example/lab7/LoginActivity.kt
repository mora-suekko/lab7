package com.example.lab7

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edUsername = findViewById<EditText>(R.id.ed_username)
        val edPassword = findViewById<EditText>(R.id.ed_password)
        val btnTogglePassword = findViewById<ImageButton>(R.id.btn_toggle_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnSignUp = findViewById<Button>(R.id.btn_signup)

        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val savedUsername = sharedPref.getString("Username", "")
        val savedPassword = sharedPref.getString("Password", "")

        btnTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            edPassword.inputType = if (isPasswordVisible) {
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            edPassword.setSelection(edPassword.text.length)
        }

        btnLogin.setOnClickListener {
            val enteredUsername = edUsername.text.toString().trim()
            val enteredPassword = edPassword.text.toString().trim()

            if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (enteredUsername == savedUsername && enteredPassword == savedPassword) {
                val editor = sharedPref.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }

        btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}