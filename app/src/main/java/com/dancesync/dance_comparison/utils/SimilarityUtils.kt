package com.dancesync.dance_comparison.utils

import com.dancesync.pose_estimation.data.Keypoint
import com.dancesync.pose_estimation.data.Skeleton
import kotlin.math.pow
import kotlin.math.sqrt

object SimilarityUtils {
    fun compareKeypoints(keypoint1: Keypoint, keypoint2: Keypoint): Double {
        val dx = keypoint1.coordinate.x - keypoint2.coordinate.x
        val dy = keypoint1.coordinate.y - keypoint2.coordinate.y
        return sqrt(dx.pow(2) + dy.pow(2)).toDouble()
    }

    fun compareSkeletons(skeleton1: Skeleton, skeleton2: Skeleton): Double {
        val keypointDistances = skeleton1.keypoints.zip(skeleton2.keypoints).map { (keypoint1, keypoint2) ->
            compareKeypoints(keypoint1, keypoint2)
        }
        return keypointDistances.average()
    }
}
