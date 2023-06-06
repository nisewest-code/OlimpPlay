package com.olim.pplay.delegate

object DelegateUtils {
    val root = BoardDelegate()
    fun settings(callback:()->Unit) = BoardDelegateSettings(callback)
    val game = BoardDelegateGame()
}