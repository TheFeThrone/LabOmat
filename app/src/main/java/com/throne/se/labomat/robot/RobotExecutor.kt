package com.throne.se.labomat.robot

import com.throne.se.labomat.robot.activities.Animation
import com.throne.se.labomat.robot.activities.Speech

class RobotExecutor {

    fun executeSay(robotHandler: RobotHandler, speech: Speech, selectedLob: String, sync: Boolean){
        robotHandler.cancelSounds()
        val sayFuture = speech.say(selectedLob, true)
        robotHandler.setSayFuture(sayFuture!!)
        if (sync){
            sayFuture.sync()
        }
    }

    fun executeAnim(robotHandler: RobotHandler, animation: Animation, randomQanimResource: Int, sync: Boolean){
        robotHandler.cancelMovements()
        val animationFuture = animation.startAnimation(randomQanimResource)
        robotHandler.setAnimationFuture(animationFuture)
        if (sync){
            animationFuture.sync()
        }
    }


}