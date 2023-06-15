package ru.alexleru.ya.todolist.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database([ToDoItemDBModel::class], version = 1, exportSchema = false)
@TypeConverters(TypeConvert::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun toDoItemDao(): ToDoItemDAO

    companion object {
        private const val DB_NAME = "todoitem.db"
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }

            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
                    .build()
                INSTANCE = db
                return db
            }

        }
    }
}