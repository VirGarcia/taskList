package com.example.tasklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tasklist.data.Task
import com.example.tasklist.data.TaskDAO

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //PRUEBAS PARA COMPROBAR QUE LA BBDD FUNCIONA
        /*val taskDAO = TaskDAO(this)

        var task = Task(-1, "Comprar leche", false)
        taskDAO.insert(task)

        var task2 = Task(-1, "Comprar pan", false)
        taskDAO.insert(task2)

        //task.done = true

        //taskDAO.update(task)
        //taskDAO.delete(task)

        //task = taskDAO.find(task.id)!!
        //Log.i("resultado",task.toString())

        //taskDAO.update(task)

        val taskList = taskDAO.findAll()
        Log.i("DATABASE", taskList.toString())*/




    }
}