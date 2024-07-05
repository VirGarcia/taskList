package com.example.tasklist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tasklist.entities.Task
import com.example.tasklist.daos.TaskDAO
import com.example.tasklist.databinding.ActivityTaskBinding
import java.util.Calendar

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding

    private lateinit var taskDAO: TaskDAO
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskDAO = TaskDAO(this)

        binding.saveButton.setOnClickListener {
            val taskName = binding.nameEditText.text.toString()

            val calendar = Calendar.getInstance()
            calendar.set(
                binding.deadlineDatePicker.year,
                binding.deadlineDatePicker.month,
                binding.deadlineDatePicker.dayOfMonth)

            val deadline = calendar.timeInMillis
            val task = Task(-1, taskName, false, deadline)

            taskDAO.insert(task)
            Toast.makeText(this, "Tarea guardada correctamente", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}