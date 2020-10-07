package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var timeWhenStopped= 0L
    private var timeElapsed = 0L
    private val KEY_TIME_ELAPSED = "elapsed time"
    private var checkStatus = false
    private val KEY_CHECK_STATUS = "on or off"
    private val KEY_CHECK_STOPPED = "time when stopped"

    //Bundle? signlas that Bundle could be a null value
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chronometer_main_timer.stop()
        Log.d(TAG, "onCreate has been called")

        // ?. builds an "if not null", short for checking if variable is null via an if statement
        // ?: is the elvis operator. What comes after is the default value if savedInstanceState is null
        timeWhenStopped = savedInstanceState?.getLong(KEY_CHECK_STOPPED) ?: 0L
        timeElapsed = savedInstanceState?.getLong(KEY_TIME_ELAPSED) ?: 0L
        checkStatus = savedInstanceState?.getBoolean(KEY_CHECK_STATUS) ?: false

        if(checkStatus) {
            chronometer_main_timer.base = SystemClock.elapsedRealtime() + timeElapsed
            chronometer_main_timer.start()
        }
        else {
            chronometer_main_timer.base = SystemClock.elapsedRealtime() + timeWhenStopped
        }

        button_main_start.setOnClickListener {
            chronometer_main_timer.base = SystemClock.elapsedRealtime() + timeWhenStopped
            chronometer_main_timer.start()
            checkStatus = true
        }

        button_main_stop.setOnClickListener {
            timeWhenStopped = chronometer_main_timer.base - SystemClock.elapsedRealtime()
            chronometer_main_timer.stop()
            checkStatus = false
        }

        button_main_reset.setOnClickListener {
            chronometer_main_timer.base = SystemClock.elapsedRealtime()
            timeWhenStopped = 0
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSavedInstanceState has been called")
        if(checkStatus) {
            outState.putBoolean(KEY_CHECK_STATUS, checkStatus)
        }
        timeElapsed = chronometer_main_timer.base - SystemClock.elapsedRealtime()
        outState.putLong(KEY_CHECK_STOPPED, timeWhenStopped)
        outState.putLong(KEY_TIME_ELAPSED, timeElapsed)
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
        timeElapsed = timeWhenStopped
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