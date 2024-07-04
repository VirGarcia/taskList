package com.example.tasklist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklist.data.Task
import com.example.tasklist.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TaskAdapter (
    private var dataSet: List<Task> = emptyList(),
    private val onItemClickListener: (Int) -> Unit,
    private val onItemDeleteClickListener: (Int) -> Unit,
    private val onItemCheckedClickListener: (Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(dataSet[position])
        holder.itemView.setOnClickListener {
            onItemClickListener(position)
        }
        holder.binding.deleteButton.setOnClickListener {
            onItemDeleteClickListener(position)
        }
        holder.binding.doneCheckBox.setOnCheckedChangeListener { checkbox, _ ->
            if (checkbox.isPressed) {
                onItemCheckedClickListener(position)
            }
        }
    }

    fun updateData(dataSet: List<Task>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }


    class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        fun render(task: Task) {
            binding.nameTextView.text = task.name
            binding.doneCheckBox.isChecked = task.done

            // Crear una instancia de Calendar y establecer su tiempo en milisegundos
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = task.deadline.toLong()

            // Crear un formato de fecha
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            // Formatear la fecha y establecerla en el TextView
            binding.deadlineTextView.text = dateFormat.format(calendar.time)
        }
    }
}