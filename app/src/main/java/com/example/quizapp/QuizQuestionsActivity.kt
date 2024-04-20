package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityQuizQuestionsBinding
import kotlin.random.Random

class QuizQuestionsActivity : AppCompatActivity() {
    private var currentQuestion:Int=1
    private lateinit var binding: ActivityQuizQuestionsBinding
    private lateinit var optionButtons: List<TextView>
    private var ansIndex:Int=0
    private var submitted:Boolean=false
    private var correctAns:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingQuestion()
        settingButtonClick()
        settingSubmitButton()
    }
    private fun settingQuestion(){
        binding.pBar.progress=currentQuestion
        binding.pTextView.text="$currentQuestion/10"
        val num1= Random.nextInt(1,21)
        val num2=Random.nextInt(1,21)
        binding.questionTextView.text="What is $num1 *$num2"
        binding.submitButton.isEnabled=false
        settingOptions(num1,num2)
        currentQuestion++
    }
    private fun settingOptions(num1:Int,num2:Int){
        binding.apply {
            val optionButtons= listOf(option1,option2,option3,option4)
            ansIndex=Random.nextInt(0,optionButtons.size)
            optionButtons[ansIndex].text=(num1*num2).toString()
            for(i in optionButtons.indices){
                if(i==ansIndex)
                    continue
                else{
                    optionButtons[i].text=(Random.nextInt(1,21)*Random.nextInt(1,21)).toString()
                }
            }
        }
    }
    private fun settingButtonClick(){
        binding.apply {
            optionButtons= listOf(option1,option2,option3,option4)
            optionButtons.forEachIndexed{index,button->
                button.setOnClickListener {
//                    If correct Button is clicked
                    if(index==ansIndex){
                        button.setBackgroundResource(R.drawable.correct_options_background)
//                        optionButtons.forEach {
//                            if(it!=optionButtons[ansIndex])
//                                it.setBackgroundColor(Color.RED)
//                        }
                        correctAns++
                    }
//                    Other option is called
                    else{
                        button.setBackgroundResource(R.drawable.wrong_option_color)
//                        optionButtons.forEachIndexed { i, b ->
//                            if(i!=ansIndex){
//                                b.setBackgroundColor(Color.RED)
//                            }
//                        }
                        optionButtons[ansIndex].setBackgroundResource(R.drawable.correct_options_background)
                    }
                    submitted=true
                    optionButtons.forEach {
                        it.isClickable=false
                    }
                    submitButton.isEnabled=true
                }
            }
//            optionButtons[ansIndex].setOnClickListener {
//                if(submitted) return@setOnClickListener
//                optionButtons[ansIndex].setBackgroundColor(Color.GREEN)
//                for(i in optionButtons){
//                    if(i!=optionButtons[ansIndex]){
//                            i.setBackgroundColor(Color.RED)
//                    }
//                }
//                submitted=true
//            }
//            for(i in optionButtons){
//                if(i==optionButtons[ansIndex]) continue
//                i.setOnClickListener {
//                    if(submitted || i==optionButtons[ansIndex]) return@setOnClickListener
//                    i.setBackgroundColor(Color.RED)
//                    optionButtons[ansIndex].setBackgroundColor(Color.GREEN)
//                }
//                submitted=true
//                break
//            }
        }
    }
    private fun settingSubmitButton(){
        binding.submitButton.setOnClickListener {
            if(currentQuestion==11){
                val playerName=intent.getStringExtra("Name")
                val intent= Intent(this@QuizQuestionsActivity,ResultScreen::class.java)
                intent.putExtra("Name",playerName)
                intent.putExtra("Score",correctAns.toString())
                startActivity(intent)
                finish()
            }
            else{
                settingQuestion()
                settingButtonClick()
            }
            clearingButtonColor();
        }
    }
    private fun clearingButtonColor(){
        binding.apply {
            optionButtons.forEach {
                    it.setBackgroundResource(R.drawable.boundary_background)
//                it.setBackgroundColor(Color.WHITE)
            }
        }
    }
}