package com.throne.se.labomat.utilities

import android.content.Context
import android.util.Log
import com.throne.se.labomat.R

class AnimLoader {
    private fun getQanimFiles(context: Context): Map<String, Int> {
        val qanimFiles = mutableMapOf<String, Int>()

        // Get the raw resources' identifiers by reflection
        val rawResources = R.raw::class.java.fields

        for (field in rawResources) {
            try {
                // Get the file name and resource ID
                val fileName = field.name
                val resourceId = field.getInt(null)

                // Check if the file name ends with ".qanim"
                qanimFiles[fileName] = resourceId

            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }

        // Log the found files for debugging
        Log.d("AnimLoader", "Found .qanim files: $qanimFiles")

        return qanimFiles
    }

    fun pickRandomQanimFile(context: Context): Pair<String, Int>? {
        val qanimFiles = getQanimFiles(context)
        val randomFile = if (qanimFiles.isNotEmpty()) {
            qanimFiles.entries.random().toPair()
        } else {
            null // Handle the case where there are no .qanim files)
        }
        return randomFile
    }
}