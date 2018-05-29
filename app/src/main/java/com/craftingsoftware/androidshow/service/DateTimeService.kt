package com.craftingsoftware.androidshow.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import com.craftingsoftware.androidshow.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

/**
 * Created by constantin.cheptea
 * on 29/05/2018.
 */
class DateTimeService : Service() {
    companion object {
        const val START_TIME = "START_TIME"
        const val STOP_TIME = "STOP_TIME"

        const val TIMER_NOTIFICATION_ID: Int = 4543543
        const val TIMER_NOTIFICATION_CHANNEL_ID = "androidshow"
        const val TIMER_NOTIFICATION_CHANNEL_NAME = "Android Show"
    }

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel

    private lateinit var timerObservable: Disposable

    override fun onCreate() {
        super.onCreate()
        setupNotificationChannel()
        Timber.d("DateTimeService is created.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            START_TIME -> startTimer()
            STOP_TIME -> stopTimer()
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun startTimer() {
        timerObservable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val time = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
                    showNotification(time)
                }
    }

    private fun stopTimer() {
        timerObservable.dispose()
        stopForeground(true)
        stopSelf()
    }

    private fun showNotification(title: String) {
        val builder = NotificationCompat.Builder(baseContext, TIMER_NOTIFICATION_CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_notification)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .addAction(0, "STOP", createPendingIntent(STOP_TIME))
                .setOngoing(true)

        startForeground(TIMER_NOTIFICATION_ID, builder.build())
    }

    private fun setupNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationChannel = NotificationChannel(TIMER_NOTIFICATION_CHANNEL_ID, TIMER_NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW)
            notificationChannel.importance = NotificationManager.IMPORTANCE_LOW
            notificationChannel.enableLights(false)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createPendingIntent(action: String, receiver: Class<*> = DateTimeService::class.java, flags: Int = 0): PendingIntent {
        val actionIntent = Intent(this, receiver)
        actionIntent.action = action

        return PendingIntent.getService(this, 0, actionIntent, flags)
    }
}