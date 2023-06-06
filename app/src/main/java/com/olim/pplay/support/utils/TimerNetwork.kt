package com.olim.pplay.support.utils

import kotlinx.coroutines.*

class TimerNetwork(private val callbackFinish: ()->Unit) {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)
    private var time = 8

    private fun startCoroutineTimer(delayMillis: Long = 0, repeatMillis: Long = 1000, action: () -> Unit) = scope.launch(
        Dispatchers.IO) {
        delay(delayMillis)
        if (repeatMillis > 0) {
            while (isActive) {
                action()
                delay(repeatMillis)
            }
        }
    }

    private lateinit var timer: Job

    fun startTimer() {
        timer = startCoroutineTimer() {
            scope.launch(Dispatchers.Main) {
                time -= 1
                if (time <= 0){
//                    callbackFinish()
                    cancelTimer()
                }
            }
        }
        timer.start()
    }


    fun cancelTimer() {
        timer.cancel()
        callbackFinish()
    }
}