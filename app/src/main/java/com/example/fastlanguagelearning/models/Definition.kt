package com.example.fastlanguagelearning.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Definition (
    val definition : String,
    val example : String? = ""
): Parcelable
