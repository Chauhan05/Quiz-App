package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            startButton.setOnClickListener {
                if(nameEditTextView.text.toString().trim().isEmpty()){
                    Toast.makeText(this@MainActivity,"Enter your Name",Toast.LENGTH_SHORT).show()
                }
                else{
//                    Will make a new activity to start the test
                    val i= Intent(this@MainActivity,QuizQuestionsActivity::class.java)
                    i.putExtra("Name",nameEditTextView.text.toString())
                    startActivity(i)
                    finish()
                }
            }
        }
    }
}