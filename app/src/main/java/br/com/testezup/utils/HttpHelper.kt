package br.com.testezup.utils

import android.nfc.Tag
import android.util.Log
import okhttp3.*
import java.io.IOException

object HttpHelper {
    private const val TAG = "http"
    private const val LOG_ON = true
    private val JSON = MediaType.parse("application/json; charset=utf-8")
    var client = OkHttpClient()

    // GET
    fun get(url: String): String{
        Log.i("#HTTP","HttpHelper.get: $url")
        val request =  Request.Builder().url(url).get().build()
        return  getJson(request)
    }

    //  POST com JSON
    fun post(url: String, json: String): String{
        val body = RequestBody.create(JSON,json)
        val request = Request.Builder().url(url).post(body).build()
        return getJson(request)
    }
    // POST com paramentros
    fun postFrom(url: String, params: Map<String, String>): String{
        val builder = FormBody.Builder()
        for ((key, value)in params){
            builder.add(key, value)
        }
        val body = builder.build()
        // Faz a request
        val request = Request.Builder().url(url).post(body).build()
        return  getJson(request)
    }

    fun delete(url:String): String{
        val request = Request.Builder().url(url).delete().build()
        return getJson(request)
    }
    // Le a resposta do servidor
    private fun getJson(request:Request): String{
        val response = client.newCall(request).execute()
        val responseBody = response.body()
        if (responseBody != null){
            val json = responseBody.string()
            Log.i("JSON", "<< : $json")
            return json
        }
        throw IOException("Erro ao fazer a requesição")
    }
    private fun log(s:String){
        if(LOG_ON){
            Log.d(TAG, s)
        }
    }
}