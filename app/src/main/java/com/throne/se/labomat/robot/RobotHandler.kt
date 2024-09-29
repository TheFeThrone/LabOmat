package com.throne.se.labomat.robot

import android.app.Activity
import com.aldebaran.qi.Future
import com.aldebaran.qi.sdk.QiContext
import com.throne.se.labomat.Init
import com.throne.se.labomat.R
import com.throne.se.labomat.robot.activities.*
import com.throne.se.labomat.utilities.Config
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

class RobotHandler(private val lookAt: LookAtTarget, private val moveTo: MoveToTarget, private val animation: Animation, private val speech: Speech, private val movement: Boolean) {

    private var lookAtFuture: Future<Void>? = null
    private var moveToFuture: Future<Void>? = null
    private var animationFuture: Future<Void>? = null
    private var sayFuture: Future<Void>? = null

    fun cancelMovements() {
        lookAtFuture?.requestCancellation()
        moveToFuture?.requestCancellation()
        animationFuture?.requestCancellation()
    }

    fun cancelSounds() {
        Thread{

        }
        sayFuture?.requestCancellation()
        sayFuture?.sync()
    }

    fun setSayFuture(sayFuture: Future<Void>) {
        this.sayFuture = sayFuture
    }

    fun setLookAtFuture(lookAtFuture: Future<Void>) {
        this.lookAtFuture = lookAtFuture
    }

    fun setMoveToFuture(moveToFuture: Future<Void>) {
        this.moveToFuture = moveToFuture
    }

    fun setAnimationFuture(animationFuture: Future<Void>) {
        this.animationFuture = animationFuture
    }

}