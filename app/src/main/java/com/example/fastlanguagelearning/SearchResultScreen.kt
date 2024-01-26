package com.example.fastlanguagelearning

import android.content.Intent
import android.graphics.Typeface
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import models.Meaning
import models.Phonetic
import models.SearchResponse

class SearchResultScreen : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var phoneticAudio: String? = null
    private var phoneticText: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val searchResponse = intent.getParcelableExtra<SearchResponse>("searchResponse")
        val speakerImageView = findViewById<ImageView>(R.id.speaker_img)
        val searchButton =  findViewById<Button>(R.id.new_search_button)

        setTextInTextView(searchResponse!!.word.capitalize(), R.id.word_title)
        setTextInTextView(getString(R.string.newSearchTitle, searchResponse.word), R.id.new_search_title)
        setTextInTextView(getString(R.string.newSearchTitle, searchResponse.word), R.id.new_search_title)

        setPhoneticTextAndAudio(searchResponse.phonetics)

        setMeaningsInScreen(searchResponse.meanings)

        speakerImageView.setOnClickListener{
            playAudio(phoneticAudio)
        }

        searchButton.setOnClickListener {
            switchToSearchScreen()
        }
    }

    private fun createTextView(params: ViewGroup.LayoutParams, isTextBold: Boolean): TextView{
        val textViewDefinition = TextView(this)
        textViewDefinition.layoutParams = params
        if(isTextBold){
            textViewDefinition.setTypeface(null, Typeface.BOLD)
        }
        return textViewDefinition
    }

    private fun setMeaningsInScreen(meanings: List<Meaning>){
        val linearLayoutMeanings = findViewById<LinearLayout>(R.id.meaning)
        var definitionCount = 0

        val textViewDefinitionParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textViewDefinitionParams.setMargins(0, 0, 0, 10)

        val textViewExampleParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textViewExampleParams.setMargins(0, 0, 0, 30)

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

    private fun setPhoneticTextAndAudio(phonetics: List<Phonetic>){
        for (phonetic in phonetics){
            if (!phonetic.audioUrl.isNullOrEmpty() and !phonetic.text.isNullOrEmpty()){
                phoneticText = phonetic.text
                phoneticAudio =  phonetic.audioUrl
                break
            }
        }
        setTextInTextView(phoneticText!!, R.id.phonetic)
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
        val intent = Intent(this, SearchScreen::class.java)
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
}