package domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Parcelize
@Serializable
data class Phonetic (
    val text : String,
    @SerialName("audio")
    val audioUrl : String
): Parcelable
