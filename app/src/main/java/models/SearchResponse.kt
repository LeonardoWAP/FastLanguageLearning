package models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class SearchResponse(
    val word : String,
    val phonetics : List<Phonetic>,
    val meanings : List<Meaning>
): Parcelable