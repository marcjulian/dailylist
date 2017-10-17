package de.squiray.dailytodo.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import de.squiray.dailytodo.R
import de.squiray.dailytodo.domain.entity.Todo
import de.squiray.dailytodo.util.extension.inflate
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(val items: MutableList<Todo>,
                  private val callback: Callback)
    : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    interface Callback {
        fun onTodoClicked(todo: Todo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(parent.inflate(R.layout.item_todo))

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], callback)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todo: Todo, callback: Callback) = with(itemView) {
            todoTitle.text = todo.todo
            setOnClickListener { callback.onTodoClicked(todo) }
        }
    }

    fun addAll(todos: List<Todo>) {
        items.addAll(todos)
        notifyDataSetChanged()
    }

    fun add(todo: Todo) {
        items.add(todo)
        notifyItemChanged(positionOf(todo))
    }

    private fun positionOf(todo: Todo) = items.indexOf(todo)
}
