package com.example.fastlanguagelearning
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

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