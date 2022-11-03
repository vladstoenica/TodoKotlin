package com.stoe.todokotlin

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

//pt recycler
class TodoAdapter (
        private val todos: MutableList<Todo>      //list e read only - asta ne lasa sa facem operatiuni
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    //viewholder holds the layout of a specific item
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //cum o sa arate view-ul
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    //bind the data from todos to our view list
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curTodo = todos[position]
        holder.itemView.apply {      //apply e ca sa nu mai scriu holder.itemview.id
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)     //ca sa vedem daca ii dam display stiked sau unstriked
            cbDone.setOnCheckedChangeListener { _, isCheckedStatus ->   // _ inseamna ca nu ne trebuie parametrul ala
                toggleStrikeThrough(tvTodoTitle, isCheckedStatus)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)   //adaugam noul task la final
    }

    fun deleteDoneTodos(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged();
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    //modifica textul ca sa fie taiat odata ce e checked - ischecked e parametrul care verifica
    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG  //editeaza textul ca sa il taie
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()   //inv e inverted
        }
    }
}