package com.example.lab7

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

        btnTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                edPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                btnTogglePassword.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
            } else {
                edPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                btnTogglePassword.setImageResource(android.R.drawable.ic_menu_view)
            }
            edPassword.setSelection(edPassword.text.length)
        }

        btnLogin.setOnClickListener {
            val enteredUsername = edUsername.text.toString()
            val enteredPassword = edPassword.text.toString()

            if (enteredUsername == "admin" && enteredPassword == "1234") {
                Toast.makeText(this, "Вход выполнен!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }

        btnSignUp.setOnClickListener {
            Toast.makeText(this, "Переход на регистрацию", Toast.LENGTH_SHORT).show()
        }
    }
}
