package com.scott.msu.geoquiz4

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.scott.msu.geoquiz4.databinding.ActivityMainBinding

//Chapter 3 Section 1
private const val TAG = "MaintActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Chapter 4 Section 1
    private val quizViewModel: QuizViewModel by viewModels()


    //Chapter 3 Section 3
    private fun checkAnswer(userAnswer:Boolean) {

        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if(userAnswer == correctAnswer) {
            //Chapter 3 Section 3
            quizViewModel.incrementCorrectlyAnswered()
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
        val percentageScore = quizViewModel.scorePercentage()

        quizViewModel.moveToNext()

        if (quizViewModel.isEndOfQuiz()) {
            val percentageMessage = String.format("%.1f%%", percentageScore)

            val toast = Toast.makeText(this, percentageMessage, Toast.LENGTH_SHORT)
            toast.show()

            quizViewModel.clearScores()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Chapter 3 Section 1
        Log.d(TAG, "onCreate (Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Chapter 4 Section 1
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
            disableAnswerButton()
        }
        binding.falseButton.setOnClickListener {
            checkAnswer(false)
            disableAnswerButton()
        }
        binding.previousButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            updateQuestion()
        }
        binding.nextButton.setOnClickListener {
            answerDisplay()
            updateQuestion()
            enableAnswerButton()
        }
        binding.questionTextView.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()

    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
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