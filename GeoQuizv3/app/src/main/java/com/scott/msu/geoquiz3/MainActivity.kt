package com.scott.msu.geoquiz3

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.scott.msu.geoquiz3.databinding.ActivityMainBinding

//Chapter 3 Section 1
private const val TAG = "MaintActivity"

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0
    //Chapter 3 Section 3
    private var correctlyAnswered = 0

    private fun checkAnswer(userAnswer:Boolean) {

        val correctAnswer = questionBank[currentIndex].answer


        val messageResId = if(userAnswer == correctAnswer) {
            //Chapter 3 Section 3
            correctlyAnswered++
            R.string.correct_toast
        }
        else{
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }

    //Chapter 3 Section 3
    private fun answerDisplay() {
        val percentageScore = (correctlyAnswered.toDouble() / questionBank.size) * 100
        val percentageMessage = String.format("%.1f%%", percentageScore)

        val toast = Toast.makeText(this, percentageMessage, Toast.LENGTH_SHORT)
        toast.show()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Chapter 3 Section 1
        Log.d(TAG, "onCreate (Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
            disableAnswerButton()
        }
        binding.falseButton.setOnClickListener {
            checkAnswer(false)
            disableAnswerButton()
        }
        binding.previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1).coerceAtLeast(0)
            updateQuestion()
        }
        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1)

            //Chapter 3 Section 3
            if (currentIndex == questionBank.size){
                answerDisplay()
                correctlyAnswered = 0
                currentIndex = 0
            }

            updateQuestion()
            enableAnswerButton()

        }
        binding.questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) / questionBank.size
            updateQuestion()
        }

    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResID
        binding.questionTextView.setText(questionTextResId)
    }

    //Chapter 3 Section 2
    private fun disableAnswerButton() {
        binding.trueButton.isEnabled = false
        binding.falseButton.isEnabled = false
    }

    private fun enableAnswerButton() {
        binding.trueButton.isEnabled = true
        binding.falseButton.isEnabled = true
    }

    // Chapter 3 Section 1
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

}