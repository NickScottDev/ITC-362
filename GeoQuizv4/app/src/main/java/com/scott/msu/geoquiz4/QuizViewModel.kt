package com.scott.msu.geoquiz4

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val CORRECTLY_ANSWERED_KEY = "CORRECTLY_ANSWERED_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex:Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY)?:0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY,value)

    private var correctlyAnswered
        get() = savedStateHandle.get(CORRECTLY_ANSWERED_KEY)?:0
        set(value) = savedStateHandle.set(CORRECTLY_ANSWERED_KEY,value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResID




    fun moveToNext() {
        currentIndex = (currentIndex + 1)
    }

    fun moveToPrevious() {
        currentIndex = (currentIndex - 1).coerceAtLeast(0)
    }

    fun clearScores() {
        currentIndex = 0
        correctlyAnswered = 0
    }

    fun incrementCorrectlyAnswered() {
        correctlyAnswered++
    }

    fun scorePercentage(): Double {
        val percentageScore = (correctlyAnswered.toDouble() / questionBank.size) * 100
        return percentageScore
    }

    fun isEndOfQuiz(): Boolean {
        var endOfQuiz = false
        if (currentIndex == questionBank.size){
            endOfQuiz = true
        }
        return endOfQuiz
    }
}