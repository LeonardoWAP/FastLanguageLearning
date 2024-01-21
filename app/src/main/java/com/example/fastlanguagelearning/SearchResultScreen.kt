package com.example.fastlanguagelearning
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/*val mock = [{"word":"education",
    "phonetic":"/ˌɛdjʊˈkeɪʃn̩/",
    "phonetics":[
    {"text":"/ˌɛdjʊˈkeɪʃn̩/",
        "audio":"https://api.dictionaryapi.dev/media/pronunciations/en/education-uk.mp3",
        "sourceUrl":"https://commons.wikimedia.org/w/index.php?curid=9014400",
        "license":{"name":"BY 3.0 US","url":"https://creativecommons.org/licenses/by/3.0/us"}},
    {"text":"/ˌɛdjʊˈkeɪʃn̩/","audio":"https://api.dictionaryapi.dev/media/pronunciations/en/education-us.mp3",
        "sourceUrl":"https://commons.wikimedia.org/w/index.php?curid=857003",
        "license":{"name":"BY-SA 3.0","url":"https://creativecommons.org/licenses/by-sa/3.0"}}],
    "meanings":[
    {"partOfSpeech":"noun",
        "definitions":[
        {"definition":"The process of imparting knowledge, skill and judgment.",
            "synonyms":[],"antonyms":[],
            "example":"2016-06-17 AROP JOSEPH \"Education is the slight hammer that breaks the yoke of ignorance, and moulds knowledge, skills, ideas, good moral values in a person be it a child, a youth or full grown adult. no matter a persons age learning never stops\"."},
        {"definition":"Facts, skills and ideas that have been learned, either formally or informally.",
            "synonyms":[],"antonyms":[],
            "example":"He has had a classical education."}],
        "synonyms":[],
        "antonyms":[]}],
    "license":{"name":"CC BY-SA 3.0","url":"https://creativecommons.org/licenses/by-sa/3.0"},
    "sourceUrls":["https://en.wiktionary.org/wiki/education"]}]*/

class SearchResultScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        fun formatText(inputText: String): SpannableStringBuilder {
            val spannableStringBuilder = SpannableStringBuilder(inputText)

            val startIndex = inputText.indexOf("[")
            val endIndex = inputText.indexOf("]")

            if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
                spannableStringBuilder.setSpan(
                    StyleSpan(Typeface.BOLD),
                    startIndex,
                    endIndex + 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            return spannableStringBuilder
        }

        val wordTitle = findViewById<TextView>(R.id.word_title)
        wordTitle.text = "Educationnnn"

        val phonetic = findViewById<TextView>(R.id.phonetic)
        phonetic.text = "/ˌedʒuˈkeɪʃn/"
        
        val meanings = findViewById<TextView>(R.id.meanings)
        meanings.text = formatText("1) [uncountable, countable] a process of teaching, training and learning," +
                " especially in schools, colleges or universities, to improve knowledge and develop skills")


    }

}