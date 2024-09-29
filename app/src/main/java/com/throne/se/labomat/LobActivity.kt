package com.throne.se.labomat

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.actuation.FreeFrame
import com.aldebaran.qi.sdk.`object`.locale.Locale
import com.google.android.material.button.MaterialButton
import com.throne.se.labomat.robot.RobotExecutor
import com.throne.se.labomat.robot.RobotHandler
import com.throne.se.labomat.robot.activities.*
import com.throne.se.labomat.utilities.AnimLoader
import com.throne.se.labomat.utilities.Config
import com.throne.se.labomat.utilities.Lobe
import kotlin.properties.Delegates


class LobActivity : PepperActivity(), RobotLifecycleCallbacks {
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
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QiSDK.register(this, this)

        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        insetsController.hide(WindowInsetsCompat.Type.statusBars())
        insetsController.hide(WindowInsetsCompat.Type.captionBar())
        insetsController.hide(WindowInsetsCompat.Type.navigationBars())

        context = this
    }

    override fun onResume() {
        super.onResume()

        val statusBackground = findViewById<ImageView>(R.id.statusBackground)
        if(!init){
            statusBackground.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.rubrot,theme))
        } else {
            statusBackground.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.black,theme))
        }
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

        val lobButton = findViewById<MaterialButton>(R.id.lobButton)
        val statusBackground = findViewById<ImageView>(R.id.statusBackground)
        val captions = findViewById<TextView>(R.id.captions)


        runOnUiThread {
            statusBackground.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.black,theme))
        }

        lobButton.setOnClickListener {
            // Retrieve password
            if(init){
                Thread {
                    try {
                        val selectedLob = Lobe().pickRandomLob()
                        val selectedAnimId = AnimLoader().pickRandomQanimFile(context)?.second

                        runOnUiThread {
                            // Reset the status background color
                            statusBackground.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.rubgruen, theme))
                            lobButton.isActivated = false
                            lobButton.isClickable = false
                            lobButton.text = ""
                            lobButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.rubgrau, theme))
                            lobButton.iconTint = ColorStateList.valueOf(resources.getColor(R.color.rubgruen, theme))
                            lobButton.strokeColor = ColorStateList.valueOf(resources.getColor(R.color.rubgruen, theme))
                            captions.text = selectedLob
                        }

                        RobotExecutor().executeSay(robotHandler, speech, selectedLob, false)
                        if (selectedAnimId != null) {
                            RobotExecutor().executeAnim(robotHandler, animation, selectedAnimId, true)
                        }

                        // If sayFuture is complete, update the UI on the main thread
                        runOnUiThread {
                            // Reset the status background color
                            statusBackground.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.black, theme))
                            lobButton.isActivated = true
                            lobButton.isClickable = true
                            lobButton.text = resources.getString(R.string.lob_button)
                            lobButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.rubgruen, theme))
                            lobButton.iconTint = ColorStateList.valueOf(resources.getColor(R.color.rubblau, theme))
                            lobButton.strokeColor = ColorStateList.valueOf(resources.getColor(R.color.rubblau, theme))
                            captions.text = ""
                        }
                    } catch (e: Exception) {
                        e.printStackTrace() // Handle any exceptions here
                    }
                }.start() // Start the thread

            } else {
                runOnUiThread {
                    Toast.makeText(this, "No Robot Focus", Toast.LENGTH_SHORT).show()

                }
            }
        }

    }

    override fun onRobotFocusLost() {
        Log.d("Robot Focus", "Lost")
    }

    override fun onRobotFocusRefused(reason: String?) {
        Log.d("Robot Focus", "Refused:\n$reason")
        TODO("Not yet implemented")
    }

}