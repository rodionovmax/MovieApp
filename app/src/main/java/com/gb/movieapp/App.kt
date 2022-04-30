package com.gb.movieapp

import android.app.Application
import androidx.room.Room
import com.gb.movieapp.model.room.ReviewDao
import com.gb.movieapp.model.room.ReviewDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: ReviewDatabase? = null
        private const val DB_NAME = "Reviews.db"
        fun getReviewsDao(): ReviewDao {
            if (db == null) {
                synchronized(ReviewDatabase::class.java) {
                    if (db == null) {
                        if (appInstance == null) throw
                        IllegalStateException("Application is null while creating DataBase")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            ReviewDatabase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.reviewsDao()
        }
    }
}