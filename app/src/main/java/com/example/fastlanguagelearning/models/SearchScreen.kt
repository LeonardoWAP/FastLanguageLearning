package com.example.fastlanguagelearning.models
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.fastlanguagelearning.R
import domain.model.SearchResponse
import kotlinx.serialization.json.Json
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class SearchScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val editText = findViewById<EditText>(R.id.input_word)
        val searchButton =  findViewById<Button>(R.id.search_button)

        val json = Json {
            ignoreUnknownKeys = true
        }

        searchButton.setOnClickListener {
            val wordToSearch = editText.text.toString()
            Thread{
                val url = URL("https://api.dictionaryapi.dev/api/v2/entries/en/${wordToSearch}")
                val conn = url.openConnection() as HttpURLConnection

                try{
                    val data = conn.inputStream.bufferedReader().readText()

                   val jsonArray = JSONArray(data)
                   val jsonStringList = mutableListOf<String>()
                    for (i in 0 until jsonArray.length()) {
                    jsonStringList.add(jsonArray.getJSONObject(i).toString())
                   }

                   val searchResponseList = jsonStringList.map {
                       json.decodeFromString<SearchResponse>(it)
                   }
                    switchToResultScreen(searchResponseList[0])
                }finally {
                    conn.disconnect()
                }
            }.start()
        }

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

    private fun switchToResultScreen(searchResponse: SearchResponse) {
        val intent = Intent(this, SearchResultScreen::class.java)
        intent.putExtra("searchResponse", searchResponse)

        startActivity(intent)
    }

}