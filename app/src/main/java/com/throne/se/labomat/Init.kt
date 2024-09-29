package com.throne.se.labomat

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.content.res.AssetManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.`object`.geometry.Vector3
import com.aldebaran.qi.sdk.builder.TransformBuilder
import com.throne.se.labomat.robot.activities.*
import com.throne.se.labomat.utilities.Config


class Init(private val qiContext: QiContext) {

    fun fullInit(activity: Activity, assets: AssetManager) {

        val pepperActivity = activity as? PepperActivity
            ?: throw IllegalArgumentException("Activity must be of type YourCustomActivity")

        val baseFrame = qiContext.mapping.makeFreeFrame()
        val robotFrame = qiContext.actuation.robotFrame()

        val transform = TransformBuilder.create().fromTranslation(Vector3(0.0,0.0,0.0))
        baseFrame.update(robotFrame, transform, 0L)

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        }

        val config = Config("config.json")
        config.loadConfig()
        pepperActivity.initConfig(config)
        pepperActivity.initMovement(qiContext)
        pepperActivity.initLocale()

        pepperActivity.initBaseFrame(baseFrame)

        pepperActivity.initSpeech(Speech(qiContext, pepperActivity.locale, config.getElement("speech_speed")!!.toInt(), config.getElement("speech_pitch")!!.toInt(),pepperActivity.movement))
        pepperActivity.initLookAt(LookAtTarget(qiContext, baseFrame, pepperActivity.movement))
        pepperActivity.initMoveTo(MoveToTarget(qiContext, baseFrame))
        pepperActivity.initAnimation(Animation(qiContext, assets))
    }
}