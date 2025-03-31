package com.example.lab7

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val edUsername = findViewById<EditText>(R.id.ed_username)
        val edPassword = findViewById<EditText>(R.id.ed_password)
        val edConfirmPwd = findViewById<EditText>(R.id.ed_confirm_pwd)
        val btnCreateUser = findViewById<Button>(R.id.btn_create_user)
        val btnBack = findViewById<Button>(R.id.btn_back)

        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        btnCreateUser.setOnClickListener {
            val username = edUsername.text.toString()
            val password = edPassword.text.toString()
            val confirmPassword = edConfirmPwd.text.toString()

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val editor = sharedPref.edit()
            editor.putString("Username", username)
            editor.putString("Password", password)
            editor.apply()

            Toast.makeText(this, "Аккаунт создан!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnBack.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
