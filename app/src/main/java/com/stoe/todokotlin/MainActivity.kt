package com.stoe.todokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //lateinit e un promise ca o sa o initializam mai incolo
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())    //ii bagam o lista goala
                    // = new TodoAdapter(empty list)

        rvTodoItems.adapter = todoAdapter;
        rvTodoItems.layoutManager = LinearLayoutManager(this);

        btnAddTodo.setOnClickListener{
            val todoTitle = etTodoTitle.text.toString();
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle);
                todoAdapter.addTodo(todo);
                etTodoTitle.text.clear();
            }
        }

        btnDeleteDoneTodos.setOnClickListener{
            todoAdapter.deleteDoneTodos()
        }


    }
}