package com.example.tasklist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasklist.R
import com.example.tasklist.adapters.TaskAdapter
import com.example.tasklist.data.Task
import com.example.tasklist.data.TaskDAO
import com.example.tasklist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: TaskAdapter

    private lateinit var taskList: List<Task>

    private lateinit var taskDAO: TaskDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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




        //con la siguiente linea ya se empieza a crear la base de datos
        taskDAO = TaskDAO(this)

        adapter = TaskAdapter(emptyList(), {
            Toast.makeText(this, "Click en tarea: ${taskList[it].name}", Toast.LENGTH_SHORT).show()
        }, {
            taskDAO.delete(taskList[it])
            Toast.makeText(this, "Tarea borrada correctamente", Toast.LENGTH_SHORT).show()
            loadData()
        }, {
            val task = taskList[it]
            task.done = !task.done
            taskDAO.update(task)
            loadData()
        })


        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.addTaskButton.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadData()
    }

    private fun loadData() {
        taskList = taskDAO.findAll()

        adapter.updateData(taskList)
    }
}