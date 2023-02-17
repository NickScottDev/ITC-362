package com.scott.msu.geoquizv2

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.scott.msu.geoquiz.R


class MainActivity : AppCompatActivity() {

    private lateinit var trueButton : Button
    private lateinit var falseButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

       /* trueButton.setOnClickListener{view: View ->
            Toast.makeText(
                this,
                R.string.true_button,
                Toast.LENGTH_SHORT)
                .show()

        }

        falseButton.setOnClickListener{view: View ->
            Toast.makeText(
                this,
                R.string.false_button,
                Toast.LENGTH_SHORT)
                .show()

        }*/

        trueButton.setOnClickListener {
            val snackBar = Snackbar.make(
                it,
                "Correct",
                Snackbar.LENGTH_LONG
            )
            snackBar.setTextColor(Color.WHITE)
            snackBar.setBackgroundTint(Color.GREEN)
            snackBar.show()
        }
        falseButton.setOnClickListener {
            val snackBar = Snackbar.make(
                it,
                "Incorrect",
                Snackbar.LENGTH_LONG
            )
            snackBar.setTextColor(Color.BLACK)
            snackBar.setBackgroundTint(Color.RED)
            snackBar.show()
        }
    }
}