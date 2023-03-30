package com.dancesync.dance_comparison.algorithm

import com.dancesync.dance_comparison.utils.PreprocessingUtils
import com.dancesync.dance_comparison.utils.SequenceAlignmentUtils
import com.dancesync.dance_comparison.utils.SimilarityUtils
import com.dancesync.pose_estimation.data.Skeleton

class DanceComparison {
    fun compareDanceSequences(sequence1: List<Skeleton>, sequence2: List<Skeleton>): Double {
        val preprocessedSequence1 = PreprocessingUtils.preprocessKeypoints(sequence1)
        val preprocessedSequence2 = PreprocessingUtils.preprocessKeypoints(sequence2)

        val alignedSequences = SequenceAlignmentUtils.alignSkeletonSequences(preprocessedSequence1, preprocessedSequence2)
        var totalScore = 0.0

        for ((skeleton1, skeleton2) in alignedSequences) {
            totalScore += SimilarityUtils.compareSkeletons(skeleton1, skeleton2)
        }

        return totalScore / alignedSequences.size
    }
}
