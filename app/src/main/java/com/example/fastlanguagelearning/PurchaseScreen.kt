package com.example.fastlanguagelearning
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class PurchaseScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)

        val changeColorBlue = findViewById<TextView>(R.id.subscribe)
        val textView = findViewById<TextView>(R.id.textView5) // Substitua R.id.seuTextView pelo ID do seu TextView

        val textoCompleto1 = "Subscribe now to get unlimited searches and full access to all features."
        val spannableString1 = Spannable.Factory.getInstance().newSpannable(textoCompleto1)

// Defina as palavras que você deseja colorir e suas cores para a primeira parte
        val palavraUnlimited = "unlimited"
        val inicioUnlimited = textoCompleto1.indexOf(palavraUnlimited)
        val fimUnlimited = inicioUnlimited + palavraUnlimited.length
        val corUnlimited = ContextCompat.getColor(this, R.color.blue) // Substitua pela sua cor desejada

        val palavraAllFeatures = "all features"
        val inicioAllFeatures = textoCompleto1.indexOf(palavraAllFeatures)
        val fimAllFeatures = inicioAllFeatures + palavraAllFeatures.length
        val corAllFeatures = ContextCompat.getColor(this, R.color.blue) // Substitua pela sua cor desejada

// Aplique o estilo bold (negrito) às palavras específicas
        spannableString1.setSpan(StyleSpan(android.graphics.Typeface.BOLD), inicioUnlimited, fimUnlimited, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString1.setSpan(StyleSpan(android.graphics.Typeface.BOLD), inicioAllFeatures, fimAllFeatures, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

// Configure a cor específica para as palavras específicas
        spannableString1.setSpan(ForegroundColorSpan(corUnlimited), inicioUnlimited, fimUnlimited, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString1.setSpan(ForegroundColorSpan(corAllFeatures), inicioAllFeatures, fimAllFeatures, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

// Configure o texto no TextView
        changeColorBlue.text = spannableString1

// Segunda parte do texto
        val textoCompleto2 = "Try 7 Days Free, then only $19,99 per year. Cancel anytime."
        val spannableString2 = Spannable.Factory.getInstance().newSpannable(textoCompleto2)

// Defina as palavras que você deseja colorir e suas cores para a segunda parte
        val palavraTry7Days = "Try 7 Days Free"
        val inicioTry7Days = textoCompleto2.indexOf(palavraTry7Days)
        val fimTry7Days = inicioTry7Days + palavraTry7Days.length
        val corTry7Days = ContextCompat.getColor(this, R.color.black) // Substitua pela sua cor desejada

        val palavraValor = "$19,99"
        val inicioValor = textoCompleto2.indexOf(palavraValor)
        val fimValor = inicioValor + palavraValor.length

// Aplique o estilo bold (negrito) às palavras específicas
        spannableString2.setSpan(StyleSpan(android.graphics.Typeface.BOLD), inicioTry7Days, fimTry7Days, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString2.setSpan(StyleSpan(android.graphics.Typeface.BOLD), inicioValor, fimValor, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

// Configure a cor específica para a palavra específica
        spannableString2.setSpan(ForegroundColorSpan(corTry7Days), inicioTry7Days, fimTry7Days, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString2.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, android.R.color.black)), inicioValor, fimValor, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

// Configure o texto no TextView
        textView.text = spannableString2



    }

}