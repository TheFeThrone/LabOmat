package com.throne.se.labomat

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.`object`.actuation.FreeFrame
import com.aldebaran.qi.sdk.`object`.locale.Language
import com.aldebaran.qi.sdk.`object`.locale.Locale
import com.aldebaran.qi.sdk.`object`.locale.Region
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.throne.se.labomat.robot.AutonomousAbilitiesToggle
import com.throne.se.labomat.robot.RobotHandler
import com.throne.se.labomat.robot.activities.*
import com.throne.se.labomat.utilities.Config
import kotlin.properties.Delegates


abstract class PepperActivity: RobotActivity(){

    open lateinit var baseFrame: FreeFrame
    open lateinit var speech: Speech
    open lateinit var lookAt: LookAtTarget
    open lateinit var config: Config
    open lateinit var moveTo: MoveToTarget
    open lateinit var animation: Animation
    open lateinit var robotHandler: RobotHandler
    open lateinit var locale: Locale
    open var movement by Delegates.notNull<Boolean>()



    fun initBaseFrame(baseFrame: FreeFrame) {
        this.baseFrame = baseFrame
    }

    fun initSpeech(speech: Speech) {
        this.speech = speech
    }

    fun initLookAt(lookAt: LookAtTarget) {
        this.lookAt = lookAt
    }

    fun initConfig(config: Config) {
        this.config = config

    }

    fun initMoveTo(moveTo: MoveToTarget) {
        this.moveTo = moveTo
    }

    fun initAnimation(animation: Animation) {
        this.animation = animation
    }

    fun initMovement(qiContext: QiContext) {
        this.movement = config.getElement("autonomous_activity").toBoolean()

        // Check for autonomous
        val autonomousAbilitiesBoolean = config.getElement("autonomous_activity").toBoolean()
        if (!autonomousAbilitiesBoolean){ AutonomousAbilitiesToggle().toggleAutonomousAbilities(qiContext) }
    }

    fun initLocale(){
        this.locale = if (config.getElement("language") == "de_DE") {
            Locale(Language.GERMAN, Region.GERMANY)
        } else {
            Locale(Language.ENGLISH, Region.UNITED_STATES)
        }
    }

    fun bindToRobot(qiContext: QiContext): Boolean {
        runOnUiThread {
            Toast.makeText(this, "Robot Focus gained", Toast.LENGTH_SHORT).show()
        }
        Log.d("Binding", "initiating")
        val initClass = Init(qiContext)
        initClass.fullInit(this, assets)

        Log.d("Binding", "returning")
        return true
    }

}