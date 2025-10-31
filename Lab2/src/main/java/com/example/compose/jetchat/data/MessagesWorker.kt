package com.example.compose.jetchat.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log

class MessagesWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val messages = RetrofitClient.api.getComments().take(5)
            Log.d("MessagesWorker", "Mensajes descargados: ${messages.size}")

            remoteMessages.clear()
            remoteMessages.addAll(
                messages.map { "💬 ${it.email}: ${it.body.take(40)}..." }
            )

            Result.success()
        } catch (e: Exception) {
            Log.e("MessagesWorker", "Error: ${e.message}")
            Result.retry()
        }
    }
}
