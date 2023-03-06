package com.bateman.msu.hw4courtcounter


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bateman.msu.hw4courtcounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   /* var scoreTeamA = 0
    var scoreTeamB = 0*/
    private lateinit var binding: ActivityMainBinding
    private val courtCounterViewModel:CourtCounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //This needs to be added so that when the inflator is called
        //it gets the values from the viewModel and displays them.
        displayForTeamA(courtCounterViewModel.displayTeamAScore())
        displayForTeamB(courtCounterViewModel.displayTeamBScore())
    }

    /**
     * Increase the score for Team A by 1 point.
     */
    fun addOneForTeamA(v: View?) {
        courtCounterViewModel.addOnToTeamA(1)
        displayForTeamA(courtCounterViewModel.displayTeamAScore())
    }

    /**
     * Increase the score for Team A by 2 points.
     */
    fun addTwoForTeamA(v: View?) {
        courtCounterViewModel.addOnToTeamA(2)
        displayForTeamA(courtCounterViewModel.displayTeamAScore())
    }

    /**
     * Increase the score for Team A by 3 points.
     */
    fun addThreeForTeamA(v: View?) {
        courtCounterViewModel.addOnToTeamA(3)
        displayForTeamA(courtCounterViewModel.displayTeamAScore())
    }

    /**
     * Increase the score for Team B by 1 point.
     */
    fun addOneForTeamB(v: View?) {
        courtCounterViewModel.addOnToTeamB(1)
        displayForTeamB(courtCounterViewModel.displayTeamBScore())
    }

    /**
     * Increase the score for Team B by 2 points.
     */
    fun addTwoForTeamB(v: View?) {
        courtCounterViewModel.addOnToTeamB(2)
        displayForTeamB(courtCounterViewModel.displayTeamBScore())
    }

    /**
     * Increase the score for Team B by 3 points.
     */
    fun addThreeForTeamB(v: View?) {
        courtCounterViewModel.addOnToTeamB(3)
        displayForTeamB(courtCounterViewModel.displayTeamBScore())
    }

    /**
     * Resets the score for both teams back to 0.
     */
    fun resetScore(v: View?) {
        courtCounterViewModel.resetScore()
        displayForTeamA(courtCounterViewModel.scoreTeamA)
        displayForTeamB(courtCounterViewModel.scoreTeamB)
    }

    /**
     * Displays the given score for Team A.
     */
    fun displayForTeamA(score: Int) {
        val scoreView = binding.teamAScore
        scoreView.text = score.toString()
    }

    /**
     * Displays the given score for Team B.
     */
    fun displayForTeamB(score: Int) {
        val scoreView = binding.teamBScore
        scoreView.text = score.toString()
    }
}