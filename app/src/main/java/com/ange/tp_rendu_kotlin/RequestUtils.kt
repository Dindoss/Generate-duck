package com.ange.tp_rendu_kotlin

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

private const val URL_API_DUCK = "https://random-d.uk/api/random"
private const val URL_API = "https://random-d.uk/api/list"
class RequestUtils {

    companion object {

        private val client = OkHttpClient()

        fun loadDuck(): DuckBean {

            //Réaliser la requête.
            val json = sendGet("$URL_API_DUCK")

            //Parser le JSON avec le bon bean et GSON
            val data = Gson().fromJson(json, DuckBean::class.java)

            //Retourner la donnée
            return data
        }

        fun loadList(): ListBean {

            //Réaliser la requête.
            val json = sendGet("$URL_API")

            //Parser le JSON avec le bon bean et GSON
            val data = Gson().fromJson(json, ListBean::class.java)

            //Retourner la donnée
            return data
        }

        fun sendGet(url: String): String {
            println("url : $url")
            //Création de la requete
            val request = Request.Builder().url(url).build()
            //Execution de la requête
            val response = client.newCall(request).execute()
            //Analyse du code retour
            return if (response.code !in 200..299) {
                throw Exception("Réponse du serveur incorrect : ${response.code}")
            } else {
                //Résultat de la requete
                response.body?.string() ?: ""
            }
        }

    }

}