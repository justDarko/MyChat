package com.solo.mychat.data.remote.dto

import com.google.gson.annotations.SerializedName

data class QuestionResponseDTO(
    @SerializedName("result")
    val result: String,
    @SerializedName("status")
    val status: Boolean
)