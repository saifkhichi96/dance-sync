package com.dancesync.dance_comparison.utils

import com.dancesync.pose_estimation.data.Skeleton

object SequenceAlignmentUtils {
    fun alignSkeletonSequences(sequence1: List<Skeleton>, sequence2: List<Skeleton>): List<Pair<Skeleton, Skeleton>> {
        val dtwMatrix = Array(sequence1.size) { DoubleArray(sequence2.size) { Double.POSITIVE_INFINITY } }

        // Initialize the first cell of the DTW matrix
        dtwMatrix[0][0] = SimilarityUtils.compareSkeletons(sequence1[0], sequence2[0])

        // Fill the DTW matrix
        for (i in sequence1.indices) {
            for (j in sequence2.indices) {
                if (i == 0 && j == 0) continue

                val cost = SimilarityUtils.compareSkeletons(sequence1[i], sequence2[j])
                dtwMatrix[i][j] = cost + minOf(
                    dtwMatrix.getOrElse(i - 1) { DoubleArray(sequence2.size) { Double.POSITIVE_INFINITY } }[j],
                    dtwMatrix[i].getOrElse(j - 1) { Double.POSITIVE_INFINITY },
                    dtwMatrix.getOrElse(i - 1) { DoubleArray(sequence2.size) { Double.POSITIVE_INFINITY } }.getOrElse(j - 1) { Double.POSITIVE_INFINITY }
                )
            }
        }

        // Backtrack through the DTW matrix to obtain the aligned sequences
        val alignedSequences = mutableListOf<Pair<Skeleton, Skeleton>>()
        var i = sequence1.size - 1
        var j = sequence2.size - 1

        while (i > 0 || j > 0) {
            alignedSequences.add(Pair(sequence1[i], sequence2[j]))

            val minIndex = arrayOf(i - 1, j - 1, j)
                .zip(arrayOf(i, j - 1, j - 1))
                .minByOrNull { (iIndex, jIndex) ->
                    dtwMatrix.getOrElse(iIndex) { DoubleArray(sequence2.size) { Double.POSITIVE_INFINITY } }.getOrElse(jIndex) { Double.POSITIVE_INFINITY }
                }

            if (minIndex != null) {
                i = minIndex.first
                j = minIndex.second
            }
        }

        alignedSequences.add(Pair(sequence1[0], sequence2[0]))
        return alignedSequences.reversed()
    }
}
