package com.example.fastlanguagelearning

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SearchResultScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val wordTitle = findViewById<TextView>(R.id.word_title)
        wordTitle.text = "Educationnnn"

        val phonetic = findViewById<TextView>(R.id.phonetic)
        phonetic.text = "/ˌedʒuˈkeɪʃn/"

        val meanings = findViewById<TextView>(R.id.meanings)
        meanings.text = formatText("1) [uncountable, countable] a process of teaching, training and learning," +
                " especially in schools, colleges or universities, to improve knowledge and develop skills")

        // Array de tópicos
        val topics = arrayOf(
            "post-secondary education",
            "a college/university education",
            "She completed her formal education in 2019.",
            "Students from lower income families are less likely to continue their education.",
            "to further/pursue your education",
            "to get/receive an education",
            "The school provides an excellent all-round"
        )

        // Obtém a referência para o LinearLayout do layout XML
        val meaningContainer = findViewById<LinearLayout>(R.id.meaning)

        // Adiciona TextViews ao LinearLayout para cada tópico
        for (topic in topics) {
            val textView = createBulletTextView(topic)
            meaningContainer.addView(textView)
        }
    }

    private fun createBulletTextView(text: String): TextView {
        val bullet = "• "
        val textView = TextView(this)
        textView.text = bullet + text
        textView.gravity = Gravity.START
        return textView
    }

    private fun formatText(inputText: String): SpannableStringBuilder {
        val spannableStringBuilder = SpannableStringBuilder(inputText)

        // Encontrar os índices dos colchetes
        val startIndex = inputText.indexOf("[")
        val endIndex = inputText.indexOf("]")

        // Se os colchetes existirem e o final for maior que o início
        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            // Separar o texto entre os colchetes
            val insideBrackets = inputText.substring(startIndex + 1, endIndex)
            // Separar o texto antes e depois dos colchetes
            val beforeBrackets = inputText.substring(0, startIndex)
            val afterBrackets = inputText.substring(endIndex + 1)

            // Aplicar negrito apenas às palavras fora dos colchetes
            spannableStringBuilder.clear()
            spannableStringBuilder.append(formatBoldText(beforeBrackets))
            spannableStringBuilder.append("[$insideBrackets]")
            spannableStringBuilder.append(formatBoldText(afterBrackets))
        }

        return spannableStringBuilder
    }

    private fun formatBoldText(inputText: String): SpannableStringBuilder {
        val spannableStringBuilder = SpannableStringBuilder(inputText)

        // Dividir o texto em palavras
        val words = inputText.split("\\s+".toRegex())

        // Aplicar negrito a todas as palavras
        for (word in words) {
            val startIndex = inputText.indexOf(word)
            val endIndex = startIndex + word.length

            spannableStringBuilder.setSpan(
                StyleSpan(Typeface.BOLD),
                startIndex,
                endIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        return spannableStringBuilder
    }
}
