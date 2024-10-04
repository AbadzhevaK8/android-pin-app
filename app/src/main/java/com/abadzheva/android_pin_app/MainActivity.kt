package com.abadzheva.android_pin_app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var pinInputText: TextView
    private var enteredPin: StringBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Обработка системных Insets для edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Инициализация TextView для отображения введенного PIN-кода
        pinInputText = findViewById(R.id.pin_input_text)

        // Обработка ввода цифр
        val buttons =
            listOf(
                findViewById<Button>(R.id.button0),
                findViewById<Button>(R.id.button1),
                findViewById<Button>(R.id.button2),
                findViewById<Button>(R.id.button3),
                findViewById<Button>(R.id.button4),
                findViewById<Button>(R.id.button5),
                findViewById<Button>(R.id.button6),
                findViewById<Button>(R.id.button7),
                findViewById<Button>(R.id.button8),
                findViewById<Button>(R.id.button9),
            )

        buttons.forEach { button ->
            button.setOnClickListener {
                if (enteredPin.length < 4) {
                    enteredPin.append(button.text)
                    pinInputText.text = enteredPin.toString()
                }
            }
        }

        // Обработка удаления последнего символа
        findViewById<Button>(R.id.buttonDelete).setOnClickListener {
            if (enteredPin.isNotEmpty()) {
                enteredPin.deleteCharAt(enteredPin.length - 1)
                pinInputText.text = enteredPin.toString()
            }
        }

        // Обработка кнопки OK для проверки PIN-кода
        findViewById<Button>(R.id.buttonOk).setOnClickListener {
            if (enteredPin.toString() == "1567") {
                Toast.makeText(this, getString(R.string.pin_correct), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.pin_incorrect), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
