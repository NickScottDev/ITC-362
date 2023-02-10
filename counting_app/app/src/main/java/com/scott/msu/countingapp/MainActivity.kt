package com.scott.msu.countingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.scott.msu.countingapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //initializing the activity elements
    private lateinit var tapButton: Button
    private lateinit var count: Counter

    //initializing the view element
    private var display: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        count = Counter()
        display = binding.tapCount
        tapButton = binding.tapButton

        tapButton = findViewById(R.id.tapButton)

        tapButton.setOnClickListener {
            count!!.addCount()

            display?.setText(count.getCount().toString())
        }
    }
}