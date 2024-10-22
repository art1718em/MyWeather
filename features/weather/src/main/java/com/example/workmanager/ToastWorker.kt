package com.example.workmanager

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

internal class ToastWorker(
    private val context: Context,
    workerParams: WorkerParameters,
) : Worker(context, workerParams) {

    override fun doWork(): Result {

        val handler = Handler(Looper.getMainLooper())
        try {
            handler.postDelayed({
                Toast.makeText(context, "Спасибо, что пользуетесь нашим приложением)", Toast.LENGTH_SHORT).show()
            }, 500)
        } catch (ex: Exception) {
            handler.postDelayed({
                Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
            }, 500)
            return Result.retry()
        }
        return Result.success()
    }
}