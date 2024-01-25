package com.example.fastlanguagelearning

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import models.SearchResponse

class SearchResultScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val searchResponse = intent.getParcelableExtra<SearchResponse>("searchResponse")

        val wordTitle = findViewById<TextView>(R.id.word_title)
        val phonetic = findViewById<TextView>(R.id.phonetic)
        val newSearchtitle = findViewById<TextView>(R.id.new_search_title)
        val searchButton =  findViewById<Button>(R.id.new_search_button)

        wordTitle.text = searchResponse!!.word.capitalize()
        phonetic.text = searchResponse.phonetics[0].text
        newSearchtitle.text = "That’s it for “${searchResponse!!.word}”! ☺️"

        val linearLayoutMeanings: LinearLayout = findViewById(R.id.meaning)

        var definitionCount = 0
        for (meaning in  searchResponse.meanings) {
            val  definitions = meaning.definitions

            for ( definition in definitions){

                val textViewDefinition = TextView(this)
                var params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 0, 10)
                textViewDefinition.layoutParams = params
                textViewDefinition.setTypeface(null, Typeface.BOLD)

                val textViewExample = TextView(this)
                 params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 0, 30)
                textViewExample.layoutParams = params

                textViewDefinition.text = addDefinitionPosition(definition.definition, meaning.partOfSpeech, definitionCount++)
                textViewExample.text = addBullet(definition.example!!)

                linearLayoutMeanings.addView(textViewDefinition)
                linearLayoutMeanings.addView(textViewExample)
            }
        }

        searchButton.setOnClickListener {
            switchToResultScreen()
        }
    }

    private fun addBullet(text: String): String {
        val bullet = "• "
        if (!text.isNullOrEmpty()){
            return  bullet + text
        }
        return text
    }

    private fun addDefinitionPosition(text: String, speech: String, position: Int): String {
        val correctPosition = position + 1
        return "$correctPosition) [ $speech ] $text"
    }

    private fun switchToResultScreen() {
        Log.d("Debug", "Switching to Result Screen, searchesCount: $searchesCount")

        handleSearchButtonClick()
    }
    private fun handleSearchButtonClick() {
        if (searchesCount < MAX_SEARCHES_PER_DAY) {
            searchesCount++
            saveSearchCountAndDate()
            switchToSearchScreen()
        } else {
            switchToPurchaseScreen()
        }
    }
    private fun switchToSearchScreen() {
        val intent = Intent(this, SearchScreen::class.java)
        startActivity(intent)

    }

    private fun switchToPurchaseScreen() {
        val intent = Intent(this, PurchaseScreen::class.java)
        startActivity(intent)
    }

    private fun saveSearchCountAndDate() {
        val editor = sharedPreferences.edit()
        editor.putInt(SEARCH_COUNT_KEY, searchesCount)
        editor.putLong(LAST_SEARCH_DATE_KEY, System.currentTimeMillis())
        editor.apply()
    }



}