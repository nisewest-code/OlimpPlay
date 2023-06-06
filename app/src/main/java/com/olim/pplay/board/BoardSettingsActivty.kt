package com.olim.pplay.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.olim.pplay.R
import com.olim.pplay.databinding.ActivityBoardSettingsActivtyBinding
import com.olim.pplay.delegate.DelegateUtils
import com.olim.pplay.support.utils.SoundService
import com.olim.pplay.support.utils.storage.Bucket

class BoardSettingsActivty : AppCompatActivity() {
    private lateinit var binding: ActivityBoardSettingsActivtyBinding
    private var isMusic = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardSettingsActivtyBinding.inflate(layoutInflater)
        val delegate = DelegateUtils.settings(this::cl)
        setContentView(binding.root)
        binding.back.setOnClickListener(delegate)
        binding.btnMusic.setOnClickListener(delegate)
        binding.reset.setOnClickListener(delegate)
        binding.player1.text = "Player1: ${Bucket.score1}"
        binding.player2.text = "Player2: ${Bucket.score2}"
        isMusic = Bucket.isMusic
    }

    private fun cl(){
        Log.e("fdf", "fdfd")
        if (isMusic) {
            stopService(Intent(this, SoundService::class.java))
            Bucket.isMusic = false
        } else {
            startService(Intent(this, SoundService::class.java))
            Bucket.isMusic = true
        }
        isMusic = !isMusic
        update()
    }

    private fun update(){
        if (isMusic){
            binding.btnMusic.text = getString(R.string.off)
        } else {
            binding.btnMusic.text = getString(R.string.on)
        }
    }
}