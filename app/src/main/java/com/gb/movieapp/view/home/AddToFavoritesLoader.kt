package com.gb.movieapp.view.home

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.gb.movieapp.model.FavoritesListDTO
import com.gb.movieapp.view.favorites.FavoritesListLoader
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class AddToFavoritesLoader(
    private val listener: AddToFavoritesLoaderListener,
    private val accountId: String,
    private val apiKey: String,
    private val sessionId: String,
) {
    @RequiresApi(Build.VERSION_CODES.N)
    fun addToFavorites() =
        try {
            val uri =
                URL("https://api.themoviedb.org/3/account/${accountId}/favorite?api_key=${apiKey}&session_id=${sessionId}")
            val handler = Handler()

            Thread {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = (uri.openConnection() as HttpsURLConnection).apply {
                        requestMethod = "POST"
                        readTimeout = 10000
                    }

                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    val response = getLines(bufferedReader)

                    val favoritesListDTO: FavoritesListDTO =
                        Gson().fromJson(response, FavoritesListDTO::class.java)

                    handler.post {
                        listener.onLoaded(favoritesListDTO)
                    }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    listener.onFailed(e)
                } finally {
                    urlConnection.disconnect()
                }
            }.start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            listener.onFailed(e)
        }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    interface AddToFavoritesLoaderListener {
        fun onLoaded(favoritesDTO: FavoritesListDTO) // Change type
        fun onFailed(throwable: Throwable)
    }
}

