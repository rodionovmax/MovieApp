package com.gb.movieapp.view.home

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.gb.movieapp.BuildConfig
import com.gb.movieapp.model.ChangeFavoritesDTO
import com.gb.movieapp.model.request.FavoritesPostModel
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection


class AddToFavoritesLoader(
    private val listener: AddToFavoritesLoaderListener,
    private val sessionId: String,
) {
    @RequiresApi(Build.VERSION_CODES.N)
    fun addToFavorites(favoritesPostModel : FavoritesPostModel) =
        try {
            val uri =
                URL("https://api.themoviedb.org/3/account/${BuildConfig.TMDB_ACCOUNT_ID}/favorite?api_key=${BuildConfig.TMDB_API_KEY}&session_id=${sessionId}")
            val handler = Handler()

            Thread {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = (uri.openConnection() as HttpsURLConnection).apply {
                        requestMethod = "POST"
                        readTimeout = 10000
                        setRequestProperty("Content-Type", "application/json; utf-8")
                        setRequestProperty("Accept", "application/json")
                        doOutput = true
                    }

                    urlConnection.outputStream.use { os ->
                        val input: ByteArray = Gson().toJson(favoritesPostModel, FavoritesPostModel::class.java).toByteArray(
                            Charsets.UTF_8)
                        os.write(input, 0, input.size)
                    }

                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    val response = getLines(bufferedReader)

                    val addedToFavorites: ChangeFavoritesDTO =
                        Gson().fromJson(response, ChangeFavoritesDTO::class.java)

                    handler.post {
                        listener.onLoaded(addedToFavorites)
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
        fun onLoaded(addedToFavorites: ChangeFavoritesDTO)
        fun onFailed(throwable: Throwable)
    }
}

