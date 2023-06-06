package com.olim.pplay.support

import android.animation.ValueAnimator
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.animation.addListener

class Ball() {
    private var valueAnimator: ValueAnimator? = null

    fun ball(v: View, startX: Float, startY: Float, endX: Float, endY: Float, def: Float, callback: (x: Float, y: Float)->Boolean){
        val currentX = startX
        var currentY = startY

        valueAnimator = ValueAnimator.ofFloat(startY, endY)

        valueAnimator?.addUpdateListener {
            val value = it.animatedValue as Float
            // 2
            v.translationY = value
            currentY = value
            if (callback(currentX, currentY))
                valueAnimator?.end()
        }
        valueAnimator?.addListener(onEnd = {
            v.y = def
        })

        valueAnimator?.interpolator = LinearInterpolator()
        valueAnimator?.duration = 1000
        valueAnimator?.start()
    }

}