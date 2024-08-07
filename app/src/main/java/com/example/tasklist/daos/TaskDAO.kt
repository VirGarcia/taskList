package com.example.tasklist.daos

import android.content.ContentValues
import android.content.Context
import com.example.tasklist.entities.Task
import com.example.tasklist.utils.DatabaseManager

class TaskDAO(context: Context) {
    private val databaseManager: DatabaseManager = DatabaseManager(context)

    fun insert(task: Task) {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Task.COLUMN_NAME_TITLE, task.name)
        values.put(Task.COLUMN_NAME_DONE, task.done)
        values.put(Task.COLUMN_NAME_DEADLINE, task.deadline)


        //lo de null hay que ponerlo por la documentación, es así.
        val newRowId = db.insert(Task.TABLE_NAME, null, values)
        task.id = newRowId.toInt()
    }

    fun update(task: Task) {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Task.COLUMN_NAME_TITLE, task.name)
        values.put(Task.COLUMN_NAME_DONE, task.done)
        values.put(Task.COLUMN_NAME_DEADLINE, task.deadline)

        val updatedRows = db.update(
            Task.TABLE_NAME,
            values,
            "${Task.COLUMN_NAME_ID} = ${task.id}",
            null
        )

    }
    fun delete(task: Task) {
        val db = databaseManager.writableDatabase
        val deletedRows = db.delete(Task.TABLE_NAME, "${Task.COLUMN_NAME_ID} = ${task.id}", null)
    }

    //buscar por id
    fun find(id: Int) : Task? {
        val db = databaseManager.readableDatabase

        val projection = arrayOf(
            Task.COLUMN_NAME_ID,
            Task.COLUMN_NAME_TITLE,
            Task.COLUMN_NAME_DONE,
            Task.COLUMN_NAME_DEADLINE
        )

        val cursor = db.query(
            Task.TABLE_NAME,                              // The table to query
            projection,                                   // The array of columns to return (pass null to get all)
            "${Task.COLUMN_NAME_ID} = $id",     // The columns for the WHERE clause
            null,                           // The values for the WHERE clause
            null,                               // don't group the rows
            null,                                // don't filter by row groups
            null                                // The sort order
        )

        var task: Task? = null
        if (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_TITLE))
            val done = cursor.getInt(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_DONE)) == 1
            val deadline = cursor.getLong(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_DEADLINE))
            task = Task(id, name, done, deadline)
        }
        cursor.close()
        db.close()
        return task
    }

    fun findAll() : List<Task> {
        val db = databaseManager.readableDatabase

        val projection = arrayOf(
            Task.COLUMN_NAME_ID,
            Task.COLUMN_NAME_TITLE,
            Task.COLUMN_NAME_DONE,
            Task.COLUMN_NAME_DEADLINE
        )

        val cursor = db.query(
            Task.TABLE_NAME,                        // The table to query
            projection,                             // The array of columns to return (pass null to get all)
            null,                            // The columns for the WHERE clause
            null,                         // The values for the WHERE clause
            null,                            // don't group the rows
            null,                             // don't filter by row groups
            null                             // The sort order
        )

        var tasks = mutableListOf<Task>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_TITLE))
            val done = cursor.getInt(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_DONE)) == 1
            val deadline = cursor.getLong(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_DEADLINE))
            val task = Task(id, name, done, deadline)
            tasks.add(task)
        }
        cursor.close()
        db.close()
        return tasks
    }





}