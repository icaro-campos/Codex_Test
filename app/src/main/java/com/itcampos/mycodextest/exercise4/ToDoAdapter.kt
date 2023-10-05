package com.itcampos.mycodextest.exercise4

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itcampos.mycodextest.databinding.RowItemLayoutBinding

class ToDoAdapter(
    private val context: Context,
    private val toDoList: MutableList<ToDoModel>,
    private val updateAndDelete: UpdateAndDelete
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(private val binding: RowItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: ToDoModel) {
            binding.textItem.text = task.itemDataText
            binding.checkboxToDo.isChecked = task.done ?: false

            binding.checkboxToDo.setOnCheckedChangeListener { _, isChecked ->
                val itemUID = task.UID
                if (itemUID != null) {
                    updateAndDelete.modifyItem(itemUID, isChecked)
                }
            }

            binding.imageButtonErase.setOnClickListener {
                val itemUID = task.UID
                if (itemUID != null) {
                    updateAndDelete.onItemDelete(itemUID)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding =
            RowItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val task = toDoList[position]
        holder.bind(task)
    }
}