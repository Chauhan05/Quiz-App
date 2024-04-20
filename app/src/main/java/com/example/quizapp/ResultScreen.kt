package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.databinding.ActivityQuizQuestionsBinding
import com.example.quizapp.databinding.ActivityResultScreenBinding

class ResultScreen : AppCompatActivity() {
    private lateinit var binding:ActivityResultScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResultScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val playerName=intent.getStringExtra("Name")
        val score=intent.getStringExtra("Score")
        binding.name.text="Name: $playerName"
        binding.scoreTextView.text="Your Score : ${score}"
        binding.startAgainButton.setOnClickListener {
            val intent=Intent(this@ResultScreen,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}