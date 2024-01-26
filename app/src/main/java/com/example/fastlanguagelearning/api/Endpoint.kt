package com.example.fastlanguagelearning.api

import com.google.gson.JsonArray;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path

interface Endpoint {
    @GET("api/v2/entries/en/{wordToSearch}")
    fun getWordMeaning(@Path(value="wordToSearch", encoded= true) wordToSearch : String) : Call<JsonArray>

}
