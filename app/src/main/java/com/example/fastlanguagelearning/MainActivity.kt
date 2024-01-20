package com.example.fastlanguagelearning

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.fastlanguagelearning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar um CountDownTimer para iniciar a SearchScreen após 5 segundos (ajuste conforme necessário)
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // O código aqui é executado a cada segundo do temporizador (opcional)
            }

            override fun onFinish() {
                // Quando o temporizador atinge zero, inicie a SearchScreen
                switchToSearchScreen()
            }
        }.start()
    }

    private fun switchToSearchScreen() {
        // Criar um Intent para a SearchScreen e iniciar a Activity
        val intent = Intent(this, SearchScreen::class.java)
        startActivity(intent)
        // Finalizar a MainActivity se desejar
        finish()
    }
}
