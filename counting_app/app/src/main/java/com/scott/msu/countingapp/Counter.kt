package com.scott.msu.countingapp

import androidx.annotation.StringRes

class Counter {
    private var count: Int=0
    fun addCount() {
        count++
    }

    fun getCount(): Int {
        return count
    }
}