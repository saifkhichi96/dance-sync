package com.dancesync.pose_estimation.utils


interface InferenceListener<Result> {

    fun onInferenceSuccess(result: Result)

    fun onInferenceFailure(e: Exception)

}