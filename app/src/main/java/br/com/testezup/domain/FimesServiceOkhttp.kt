package br.com.testezup.domain

import br.com.testezup.utils.HttpHelper


object FilmesServiceOkhttp{
    private const val BASE_URL = "http://www.omdbapi.com/?i=tt3896198&apikey=98a43b07"

    fun getFilme(): String{
        val json = HttpHelper.get(BASE_URL)
        return  json
    }
}