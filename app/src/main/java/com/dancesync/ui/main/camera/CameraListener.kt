package com.dancesync.ui.main.camera

import android.graphics.Bitmap

interface CameraListener {
    fun onFrame(image: Bitmap)

    fun onFrameInfo(fps: Int, timeNano: Long)

}