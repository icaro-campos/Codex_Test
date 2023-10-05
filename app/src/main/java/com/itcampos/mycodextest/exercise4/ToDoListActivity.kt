package com.itcampos.mycodextest.exercise4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.itcampos.mycodextest.databinding.ActivityToDoListBinding

class ToDoListActivity : AppCompatActivity(), View.OnClickListener, UpdateAndDelete {

    private lateinit var binding: ActivityToDoListBinding
    private lateinit var database: DatabaseReference
    private var toDoList: MutableList<ToDoModel>? = null
    private lateinit var adapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        database = FirebaseDatabase.getInstance().reference

        binding.fab.setOnClickListener(this)

        toDoList = mutableListOf()
        adapter = ToDoAdapter(this, toDoList!!, this)
        binding.recyclerviewToDo.adapter = adapter
        binding.recyclerviewToDo.layoutManager = LinearLayoutManager(this)

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                toDoList!!.clear()
                addItemToList(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Sem tarefa adicionada", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun addItemToList(snapshot: DataSnapshot) {
        val items = snapshot.children.iterator()
        if (items.hasNext()) {
            val toDoIndexedValue = items.next()
            val itemsIterator = toDoIndexedValue.children.iterator()

            while (itemsIterator.hasNext()) {
                val currentItem = itemsIterator.next()
                val toDoItemData = ToDoModel.createList()
                val map = currentItem.getValue() as HashMap<String, Any>

                toDoItemData.UID = currentItem.key
                toDoItemData.done = map.get("done") as Boolean?
                toDoItemData.itemDataText = map.get("itemDataText") as String?
                toDoList!!.add(toDoItemData)
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun onClick(view: View) {
        val alertDialog = AlertDialog.Builder(this)
        val textEditText = EditText(this)

        alertDialog.setMessage("Adicione uma tarefa")
        alertDialog.setTitle("Entre com a tarefa")
        alertDialog.setView(textEditText)
        alertDialog.setPositiveButton("Adicionar") { dialog, i ->
            val todoItemData = ToDoModel.createList()
            todoItemData.itemDataText = textEditText.text.toString()
            todoItemData.done = false

            val newItemData = database.child("todo").push()
            todoItemData.UID = newItemData.key

            newItemData.setValue(todoItemData)

            dialog.dismiss()
            Toast.makeText(this, "Tarefa Adicionada", Toast.LENGTH_LONG).show()
        }
        alertDialog.show()
    }

    override fun modifyItem(itemUID: String, isDone: Boolean) {
        val itemReference = database.child("todo").child(itemUID)
        itemReference.child("done").setValue(isDone)
    }

    override fun onItemDelete(itemUID: String) {
        val itemReference = database.child("todo").child(itemUID)
        itemReference.removeValue()
        adapter.notifyDataSetChanged()
    }
}
