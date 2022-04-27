package com.gb.movieapp.view.details

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.gb.movieapp.model.MovieDetailsDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection


// TODO: Can be deleted
class MovieDetailsLoader(
    private val listener: MovieDetailsLoaderListener,
    private val movieId: Int, private val apiKey: String
) {
    @RequiresApi(Build.VERSION_CODES.N)
    fun loadMovieDetails() =
        try {
            val uri =
                URL("https://api.themoviedb.org/3/movie/${movieId}?api_key=${apiKey}")
            val handler = Handler()

            Thread {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = (uri.openConnection() as HttpsURLConnection).apply {
                        requestMethod = "GET"
                        readTimeout = 10000
                    }

                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    val response = getLines(bufferedReader)

                    val movieDetailsDTO: MovieDetailsDTO =
                        Gson().fromJson(response, MovieDetailsDTO::class.java)

                    handler.post {
                        listener.onLoaded(movieDetailsDTO)
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

    interface MovieDetailsLoaderListener {
        fun onLoaded(movieDetailsDTO: MovieDetailsDTO)
        fun onFailed(throwable: Throwable)
    }
}