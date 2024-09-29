package com.throne.se.labomat

import android.os.Bundle
import android.util.Log
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.actuation.FreeFrame
import com.aldebaran.qi.sdk.`object`.locale.Locale
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.throne.se.labomat.robot.RobotHandler
import com.throne.se.labomat.robot.activities.*
import com.throne.se.labomat.utilities.Config
import kotlin.properties.Delegates


class MainActivity : PepperActivity(), RobotLifecycleCallbacks {
    override lateinit var baseFrame: FreeFrame
    override lateinit var speech: Speech
    override lateinit var lookAt: LookAtTarget
    override lateinit var config: Config
    override lateinit var moveTo: MoveToTarget
    override lateinit var animation: Animation
    override lateinit var robotHandler: RobotHandler
    override lateinit var locale: Locale
    override var movement by Delegates.notNull<Boolean>()

    private var init = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Register the RobotLifecycleCallbacks to this Activity.
        QiSDK.register(this, this)
    }

    override fun onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this)
        super.onDestroy()
    }

    override fun onRobotFocusGained(qiContext: QiContext) {
        // The robot focus is gained.
        val qi = qiContext.toString()
        Log.d("Robot Focus", "Gained:$qi")

        if (!init) {
            Log.d("Robot Handler", "binding")
            init = this.bindToRobot(qiContext)
            Log.d("Robot Handler", "constructing")
            robotHandler = RobotHandler(lookAt, moveTo, animation, speech, movement)
            Log.d("Robot Handler", "existing")
        }

    }

    override fun onRobotFocusLost() {
        // The robot focus is lost.
        // TODO: something
        Log.d("Robot Focus", "Lost")
    }

    override fun onRobotFocusRefused(reason: String) {
        // The robot focus is refused.
        // TODO: something
        Log.d("Robot Focus", "Refused:\n$reason")
    }
}