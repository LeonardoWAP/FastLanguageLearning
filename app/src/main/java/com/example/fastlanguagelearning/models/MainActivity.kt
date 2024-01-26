package com.example.fastlanguagelearning.models

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.fastlanguagelearning.R
import com.example.fastlanguagelearning.databinding.ActivityMainBinding
import local.db.AppDataBase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,  getString(R.string.app_database_name)
        ).build()



        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                switchToSearchScreen()
            }
        }.start()
    }
    private fun switchToSearchScreen() {
        val intent = Intent(this, SearchScreen::class.java)
        startActivity(intent)
        finish()
    }
}
