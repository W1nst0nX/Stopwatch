package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    var timeWhenStopped:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chronometer_main_timer.stop()

        button_main_start.setOnClickListener {
            chronometer_main_timer.base = SystemClock.elapsedRealtime() + timeWhenStopped
            chronometer_main_timer.start()
        }

        button_main_stop.setOnClickListener {
            timeWhenStopped = chronometer_main_timer.base - SystemClock.elapsedRealtime()
            chronometer_main_timer.stop()
        }

        button_main_reset.setOnClickListener {
            chronometer_main_timer.base = SystemClock.elapsedRealtime()
            timeWhenStopped = 0
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart has been called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume has been called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause has been called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop has been called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart has been called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy has been called")
    }
}