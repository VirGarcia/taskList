package com.example.tasklist.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tasklist.data.entities.Category
import com.example.tasklist.entities.Task

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "TaskList.db"
        const val COLUMN_NAME_ID = "id"
    }

    //En esta clase se crean los métodos obligatorios como el onCreate y el onUpgrade que obliga el SQLiteOpenHelper
    //luego ya el resto de métodos del crud se crean en TASKDAO
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Category.SQL_CREATE_TABLE)
        db.execSQL(Task.SQL_CREATE_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(Task.SQL_DROP_TABLE)
        db.execSQL(Category.SQL_DROP_TABLE)
        onCreate(db)
    }

    //Quizás se meteria aqui el onOpen se llama cada vez q se hace una conexion a la BBDD
    //para activar las FK se usa el onOpen
    //onDowngrade no es obligatorio, se podría quitar.
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}