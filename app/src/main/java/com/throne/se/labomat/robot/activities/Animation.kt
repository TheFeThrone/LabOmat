package com.throne.se.labomat.robot.activities

import android.content.res.AssetManager
import android.os.Environment

import android.util.Log
import com.aldebaran.qi.Future
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.builder.AnimateBuilder
import com.aldebaran.qi.sdk.builder.AnimationBuilder
import java.io.File
import java.io.FileNotFoundException
import java.nio.charset.Charset

class Animation(private val qiContext: QiContext, assets: AssetManager) {

    private var animationFuture: Future<Void>? = null

    fun startAnimation(animResource: Int): Future<Void> {
        animationFuture?.requestCancellation()

        return buildAndRunAnimation(animResource)
    }

    private fun buildAndRunAnimation(animResource: Int): Future<Void> {

        val animationBuild = AnimationBuilder.with(qiContext).withResources(animResource).build()
        val animate = AnimateBuilder.with(qiContext).withAnimation(animationBuild).build()
        return animate.async().run().also { animationFuture = it }
    }

}
