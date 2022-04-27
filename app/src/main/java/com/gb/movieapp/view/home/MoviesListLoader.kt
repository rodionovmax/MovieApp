package com.gb.movieapp.view.home

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.gb.movieapp.model.MovieDetailsDTO
import com.gb.movieapp.model.MovieListDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection


// TODO: Can be deleted
@RequiresApi(Build.VERSION_CODES.N)
class MoviesListLoader(
    private val listener: MoviesLoaderListener,
    private val sectionId: Int,
    private val apiKey: String,
) {
    @RequiresApi(Build.VERSION_CODES.N)
    fun loadMoviesSectionList() =
        try {
            val uri = when(sectionId) {
                0 -> URL("https://api.themoviedb.org/3/movie/popular?api_key=${apiKey}")
                1 -> URL("https://api.themoviedb.org/3/movie/now_playing?api_key=${apiKey}")
                2 -> URL("https://api.themoviedb.org/3/movie/upcoming?api_key=${apiKey}")
                3 -> URL("https://api.themoviedb.org/3/movie/top_rated?api_key=${apiKey}")
                else -> URL("https://api.themoviedb.org/3/movie/popular?api_key=${apiKey}") // TODO: Throw error here instead of placeholder
            }
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

                    val moviesListDTO: MovieListDTO =
                        Gson().fromJson(response, MovieListDTO::class.java)

                    handler.post {
                        listener.onLoaded(moviesListDTO)
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

    interface MoviesLoaderListener {
        fun onLoaded(moviesDTO: MovieListDTO)
        fun onFailed(throwable: Throwable)
    }
}