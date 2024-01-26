package com.example.fastlanguagelearning.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable
@Parcelize
@Serializable
data class Meaning (
    val definitions : List<Definition>,
    val partOfSpeech: String
): Parcelable
