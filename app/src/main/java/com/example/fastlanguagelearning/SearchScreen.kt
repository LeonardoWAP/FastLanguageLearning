package com.example.fastlanguagelearning
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.fastlanguagelearning.api.Endpoint
import com.example.fastlanguagelearning.util.NetworkUtils
import com.google.gson.JsonArray
import kotlinx.serialization.json.Json
import models.SearchResponse
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Response

class SearchScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val insertedWordEditText = findViewById<EditText>(R.id.input_word)
        val searchButton =  findViewById<Button>(R.id.search_button)

        searchButton.setOnClickListener {
            val wordToSearch = insertedWordEditText.text.toString()
            Thread{
                getWordMeaning(wordToSearch)
            }.start()
        }

        insertedWordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(insertedWord: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(insertedWord: CharSequence?, start: Int, before: Int, count: Int) {
                showSearchButton(insertedWord, searchButton)
            }

            override fun afterTextChanged(insertedWord: Editable?) {
                setInsertedWordBold(insertedWord, insertedWordEditText)
            }
        })
    }

    fun setInsertedWordBold(insertedWord: CharSequence?, editText: EditText){
        if (insertedWord.isNullOrEmpty()) {
            editText.setTypeface(null, android.graphics.Typeface.NORMAL)
        } else {
            editText.setTypeface(null, android.graphics.Typeface.BOLD)
        }
    }

    fun showSearchButton(insertedWord: CharSequence?, searchButton: Button){
        if (insertedWord.isNullOrEmpty()) {
            searchButton.visibility =  View.INVISIBLE
        } else {
            searchButton.visibility =  View.VISIBLE
        }
    }
    private fun getWordMeaning(word: String){
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.dictionaryapi.dev/", this )
        val endpoint = retrofitClient.create(Endpoint::class.java)

        val json = Json {
            ignoreUnknownKeys = true
        }

        endpoint.getWordMeaning(word).enqueue(object : retrofit2.Callback<JsonArray>{
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                if (response.raw().cacheResponse() != null)  {
                    Log.d("API Call", "Cache")
                }

               val jsonArray = JSONArray(response.body().toString())

               val jsonStringList = mutableListOf<String>()
               for (i in 0 until jsonArray.length()) {
                   jsonStringList.add(jsonArray.getJSONObject(i).toString())
               }
                val searchResponseList = jsonStringList.map {json.decodeFromString<SearchResponse>(it)}

                switchToResultScreen(searchResponseList[0])
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.d("Failure to search word", word)
            }

        })
    }

    private fun switchToResultScreen(searchResponse: SearchResponse) {
        val intent = Intent(this, SearchResultScreen::class.java)
        intent.putExtra("searchResponse", searchResponse)
        startActivity(intent)
    }

}