package com.dancesync.pose_estimation.utils

import android.content.Context
import android.graphics.Bitmap
import com.dancesync.pose_estimation.data.Device
import com.dancesync.pose_estimation.model.MoveNet
import com.dancesync.pose_estimation.model.PoseDetector
import com.dancesync.ui.main.PoseListener

class InferenceEngine(
    context: Context,
    device: Device,
    private val poseListener: PoseListener,
) {

    private val lock = Any()

    private var poseDetector: PoseDetector = MoveNet.create(context, device)

    fun inference(bitmap: Bitmap) = synchronized(lock) {
        val pose = poseDetector.estimatePoses(bitmap)
        poseListener.onInferenceSuccess(Pair(bitmap, pose))
    }

    fun close() {
        poseDetector.close()
    }

}