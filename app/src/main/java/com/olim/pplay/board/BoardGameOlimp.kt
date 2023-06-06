package com.olim.pplay.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.olim.pplay.databinding.ActivityBoardGameOlimpBinding
import com.olim.pplay.delegate.DelegateUtils
import com.olim.pplay.support.Ball
import com.olim.pplay.support.GameSupport
import com.olim.pplay.support.utils.storage.Bucket
import java.lang.Math.abs

class BoardGameOlimp : AppCompatActivity() {
    private var scoreP1 = 0
    private var scoreP2 = 0
    private lateinit var binding: ActivityBoardGameOlimpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardGameOlimpBinding.inflate(layoutInflater)
        val delegate = DelegateUtils.game
        setContentView(binding.root)

        binding.myach.setOnClickListener(delegate)
        val supp = GameSupport(binding, callbackPerson1 = { x, y ->
            val myachX =binding.myach.x
            val myachY =binding.myach.y
            if (abs(x-myachX)<=80 && abs(y-myachY)<=80){
                scoreP1++
                Bucket.score1 = scoreP1
                updateScoreP1()
                val dm = resources.displayMetrics
                val k = binding.myach.y+binding.myach.height
                Ball().ball(binding.myach, binding.myach.x, binding.myach.y-k, binding.myach.x, dm.heightPixels.toFloat(), binding.myach.y) { x, y ->
                    return@ball false
                }
            }
        }, callbackPerson2 = { x, y ->
            val myachX =binding.myach.x
            val myachY =binding.myach.y
            if (abs(x-myachX)<=80 && abs(y-myachY)<=80){
                scoreP2++
                Bucket.score2 = scoreP2
                updateScoreP2()
                val k = binding.myach.y+binding.myach.height
                Ball().ball(binding.myach, binding.myach.x, binding.myach.y-k, binding.myach.x, 0f-k, binding.myach.y) { x, y ->
                    return@ball false
                }
            }
        })
        binding.container.setOnDragListener(supp.maskDragListener)
        supp.attachViewDragListener()
        updateScoreP1()
        updateScoreP2()
    }

    private fun updateScoreP1(){
        binding.scoreP1.text = "Player1: $scoreP1"
    }

    private fun updateScoreP2(){
        binding.scoreP2.text = "Player2: $scoreP2"
    }
}