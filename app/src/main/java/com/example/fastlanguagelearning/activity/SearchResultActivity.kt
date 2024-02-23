package com.example.fastlanguagelearning.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fastlanguagelearning.R
import com.example.fastlanguagelearning.models.Meaning
import com.example.fastlanguagelearning.models.Phonetic
import com.example.fastlanguagelearning.models.SearchResponse

class SearchResultActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var phoneticAudio: String? = null
    private var phoneticText: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val searchResponse = intent.getParcelableExtra<SearchResponse>("searchResponse")

        val speakerButton = findViewById<Button>(R.id.speaker_button)
        val searchButton =  findViewById<Button>(R.id.new_search_button)
        val phoneticLine = findViewById<LinearLayout>(R.id.phonetic_line)
        speakerButton.isEnabled = false

        setTextInTextView(searchResponse!!.word.capitalize(), R.id.word_title)
        setTextInTextView(getString(R.string.new_search_title, searchResponse.word), R.id.new_search_title)
        setTextInTextView(getString(R.string.new_search_title, searchResponse.word),R.id.new_search_title)

        setPhoneticTextAndAudio(searchResponse.phonetics, speakerButton, phoneticLine)

        setMeaningsInScreen(searchResponse.meanings)

        speakerButton.setOnClickListener{
            playAudio(phoneticAudio)
        }

        searchButton.setOnClickListener {
            switchToSearchScreen()
        }
    }

    private fun createTextView(params: ViewGroup.LayoutParams, isTextBold: Boolean): TextView{
        val textViewDefinition = TextView(this)
        textViewDefinition.layoutParams = params
        textViewDefinition.setTextColor(Color.parseColor("#052D39"))
        if(isTextBold){
            textViewDefinition.setTypeface(null, Typeface.BOLD)
        }
        return textViewDefinition
    }

    private fun setMeaningsInScreen(meanings: List<Meaning>){
        val linearLayoutMeanings = findViewById<LinearLayout>(R.id.meanings_section)
        var definitionCount = 0

        val textViewDefinitionParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val textViewExampleParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textViewExampleParams.setMargins(0, 0, 0, 5)

        for (meaning in  meanings) {
            val  definitions = meaning.definitions
            for ( definition in definitions){

                val textViewDefinition =  createTextView(textViewDefinitionParams,  true)
                textViewDefinition.text = formatDefinitionText(definition.definition, meaning.partOfSpeech, definitionCount++)

                val textViewExample = createTextView(textViewExampleParams, false)
                textViewExample.text = formatExampleText(definition.example!!)

                linearLayoutMeanings.addView(textViewDefinition)
                linearLayoutMeanings.addView(textViewExample)
            }
        }
    }

    private fun setPhoneticTextAndAudio(phonetics: List<Phonetic>, speakerButton: Button, phoneticLine: LinearLayout ){
        for (phonetic in phonetics){
            if (!phonetic.audioUrl.isNullOrEmpty() and !phonetic.text.isNullOrEmpty()){
                phoneticText = phonetic.text
                phoneticAudio =  phonetic.audioUrl
                speakerButton.isEnabled = true
                break
            }
        }
        setTextInTextView(phoneticText?:" ", R.id.phonetic_text)
        checkAndRemoveAudioButton(phonetics, speakerButton, phoneticLine)
    }

    private fun setTextInTextView(text: String, textViewId: Int){
        val textView = findViewById<TextView>(textViewId)
        textView.text = text
    }

    private fun formatExampleText(text: String): String {
        val bullet = "• "
        if (!text.isNullOrEmpty()){
            return  bullet + text
        }
        return text
    }

    private fun formatDefinitionText(text: String, speech: String, position: Int): String {
        val correctPosition = position + 1
        return "$correctPosition) [ $speech ] $text"
    }

    private fun switchToSearchScreen() {
        val intent = Intent(this, SearchActivity::class.java)
        intent.putExtra("openKeyboard", true)
        startActivity(intent)
        finish()
    }

    private fun playAudio(audioUrl: String?) {
        if (!audioUrl.isNullOrEmpty()) {
            try {
                if (mediaPlayer == null) {
                    val audioAttributes = AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()

                    mediaPlayer = MediaPlayer()
                    mediaPlayer?.setAudioAttributes(audioAttributes)
                    mediaPlayer?.setDataSource(audioUrl)
                    mediaPlayer?.prepareAsync()

                    mediaPlayer?.setOnPreparedListener {
                        it.start()
                    }

                    mediaPlayer?.setOnCompletionListener {
                        it.release()
                        mediaPlayer = null
                    }
                } else {
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
            } catch (e: Exception) {
                Log.e("SearchResultScreen", "Erro ao reproduzir áudio", e)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun checkAndRemoveAudioButton(phonetics: List<Phonetic>, speakerButton: Button, phoneticLine: LinearLayout) {
        var hasAudio = false
        for (phonetic in phonetics) {
            if (!phonetic.audioUrl.isNullOrEmpty()) {
                hasAudio = true
                break
            }
        }
        if (!hasAudio) {
            phoneticLine.visibility = View.GONE
        } else {
            phoneticLine.visibility = View.VISIBLE
            speakerButton.visibility = View.VISIBLE
        }
    }
}