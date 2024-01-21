package com.example.fastlanguagelearning
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

val mock = [{"word":"education",
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
    "sourceUrls":["https://en.wiktionary.org/wiki/education"]}]

mock.map

class SearchScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val editText = findViewById<EditText>(R.id.input_word)
        val searchButton =  findViewById<Button>(R.id.search_button)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    searchButton.visibility =  View.INVISIBLE
                } else {
                    searchButton.visibility =  View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    editText.setTypeface(null, android.graphics.Typeface.NORMAL)
                } else {
                    editText.setTypeface(null, android.graphics.Typeface.BOLD)
                }
            }
        })

    }

}