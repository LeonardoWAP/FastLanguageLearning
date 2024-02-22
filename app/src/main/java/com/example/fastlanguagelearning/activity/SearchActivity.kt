package com.example.fastlanguagelearning.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.fastlanguagelearning.R
import com.example.fastlanguagelearning.api.Endpoint
import com.example.fastlanguagelearning.dao.RequestCountDao
import com.example.fastlanguagelearning.db.DataBaseManager
import com.example.fastlanguagelearning.entity.RequestCount
import com.example.fastlanguagelearning.models.SearchResponse
import com.example.fastlanguagelearning.util.NetworkUtils
import com.google.gson.JsonArray
import kotlinx.serialization.json.Json
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Response
import java.util.Calendar


class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val insertedWordEditText = findViewById<EditText>(R.id.input_word)

        if (intent.getBooleanExtra("openKeyboard", false)) {
            insertedWordEditText.requestFocus()
        }

        val searchButton =  findViewById<Button>(R.id.search_button)
        val progressBar =  findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.visibility =  View.INVISIBLE

        searchButton.setOnClickListener {
            val wordToSearch = insertedWordEditText.text.toString()
            searchButton.visibility =  View.INVISIBLE
            progressBar.visibility =  View.VISIBLE
            Thread{

                val today =  getDayInMillis()
                val db = DataBaseManager.getDatabase(this)
                val requestCountDao = db.requestCountDao()
                requestCountDao.deleteAll() // TODO: apagar isso  

                val requestToday = requestCountDao.getByDay(today)

                val count: Int = requestToday?.count ?: 0
                val countIncremented = count +1

                getWordMeaning(wordToSearch, countIncremented, today, requestCountDao)
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
    private fun getWordMeaning(word: String, count: Int, date:Long, requestCountDao: RequestCountDao){
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.dictionaryapi.dev/", this )
        val endpoint = retrofitClient.create(Endpoint::class.java)

        val json = Json {
            ignoreUnknownKeys = true
        }
        if (count <= 10 ){
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

                    Thread{
                        requestCountDao.upsert(RequestCount(count= count, requestDate= date))
                    }.start()

                    switchToResultScreen(searchResponseList[0])
                }

                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    Log.d("Failure to search word", word)
                }

            })

        }else{
            switchToPurchaseScreen()
        }
    }

    private fun switchToResultScreen(searchResponse: SearchResponse) {
        val intent = Intent(this, SearchResultActivity::class.java)
        intent.putExtra("searchResponse", searchResponse)
        startActivity(intent)
    }
    private fun switchToPurchaseScreen() {
        val intent = Intent(this, PurchaseActivity::class.java)
        startActivity(intent)
    }

    fun getDayInMillis(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.timeInMillis
    }

}