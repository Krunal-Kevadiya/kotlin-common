package com.kotlinlibrary.permission

import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs

object Try {
    private const val DELAY = 2000

    fun withUiThread(runnable: Runnable?, tryCount: Int) {
        runnable?.let {
            runTryCycle(it, tryCount)
        }
    }

    fun withThreadIfFail(action: () -> Unit, tryCount: Int) {
        action.let {
            try {
                Runnable(it).run()
            } catch (e: Exception) {
                logs("Attempt in UI thread fail!", LogType.DEBUG)
                Thread { runTryCycle(Runnable(it), tryCount) }.start()
            }
        }
    }

    fun withThread(action: () -> Unit, tryCount: Int) {
        Thread { runTryCycle(Runnable(action), tryCount) }.start()
    }

    private fun runTryCycle(runnable: Runnable?, tryCount: Int) {
        runnable?.let {
            var tryCountLocal = tryCount
            var attempt = 1
            while (tryCountLocal > 0) {
                try {
                    logs("Attempt $attempt", LogType.DEBUG)
                    it.run()
                } catch (e: Exception) {
                    logs("Attempt $attempt fail!", LogType.WARN)
                    attempt++
                    tryCountLocal--
                    try {
                        Thread.sleep(DELAY.toLong())
                    } catch (e1: InterruptedException) {
                        e1.printStackTrace()
                    }

                    continue
                }

                tryCountLocal = 0
            }
        }
    }
}
