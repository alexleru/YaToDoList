package ru.alexleru.ya.todolist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.UUID

@Dao
interface ToDoItemDAO {

    @Query("SELECT * FROM TODOITEM")
    fun getToDoList(): LiveData<List<ToDoItemDBModel>>

    @Query("SELECT * FROM TODOITEM WHERE ID =:id LIMIT 1")
    fun getToDoItemByID(id: UUID): ToDoItemDBModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToDoItem(toDoItemDBModel: ToDoItemDBModel)

    @Query("DELETE FROM TODOITEM WHERE ID=:id")
    fun deleteToDoItemById(id: UUID)
}