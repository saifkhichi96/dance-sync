package com.dancesync.dance_comparison.utils

import android.graphics.PointF
import com.dancesync.pose_estimation.data.Keypoint
import com.dancesync.pose_estimation.data.Skeleton
import kotlin.math.pow
import kotlin.math.sqrt

object PreprocessingUtils {
    fun preprocessKeypoints(skeletons: List<Skeleton>): List<Skeleton> {
        return skeletons.map { normalizeSkeleton(it) }
    }

    private fun normalizeSkeleton(skeleton: Skeleton): Skeleton {
        val hipIndex = 11 // Index of the left hip keypoint in COCO Keypoints dataset
        val neckIndex = 5 // Index of the neck keypoint in COCO Keypoints dataset
        val hip = skeleton.keypoints[hipIndex]
        val neck = skeleton.keypoints[neckIndex]
        val torsoLength = euclideanDistance(hip, neck)

        val normalizedKeypoints = skeleton.keypoints.map { keypoint ->
            val newX = (keypoint.coordinate.x - hip.coordinate.x) / torsoLength
            val newY = (keypoint.coordinate.y - hip.coordinate.y) / torsoLength
            Keypoint(keypoint.bodyPart, PointF(newX, newY), keypoint.score)
        }

        return Skeleton(skeleton.id, normalizedKeypoints, skeleton.score)
    }

    private fun euclideanDistance(keypoint1: Keypoint, keypoint2: Keypoint): Float {
        return sqrt((keypoint1.coordinate.x - keypoint2.coordinate.x).pow(2) + (keypoint1.coordinate.y - keypoint2.coordinate.y).pow(2))
    }
}

