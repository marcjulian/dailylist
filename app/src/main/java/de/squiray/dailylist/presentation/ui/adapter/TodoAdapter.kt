package de.squiray.dailylist.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import de.squiray.dailylist.R
import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.util.extension.inflate
import kotlinx.android.synthetic.main.item_todo.view.*


class TodoAdapter(private val items: MutableList<Todo>,
                  private val callback: Callback)
    : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    interface Callback {
        fun onTodoClicked(todo: Todo)
        fun onCompleteTodoClicked(todo: Todo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(parent.inflate(R.layout.item_todo))

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], callback)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todo: Todo, callback: Callback) = with(itemView) {
            completeTodo.isChecked = false
            todoTitle.text = todo.todo
            completeTodo.setOnCheckedChangeListener { _, _ -> callback.onCompleteTodoClicked(todo) }
            setOnClickListener { callback.onTodoClicked(todo) }
        }
    }

    fun addAll(todos: List<Todo>) {
        items.addAll(todos)
        notifyDataSetChanged()
    }

    fun add(todo: Todo) {
        items.add(todo)
        notifyItemInserted(positionOf(todo))
    }

    fun replace(todo: Todo) {
        replace(positionOf(todo), todo)
    }

    fun replace(position: Int, todo: Todo) {
        items.set(position, todo)
        notifyItemChanged(position)
    }

    fun addOrReplace(todo: Todo) {
        if (contains(todo)) {
            replace(todo)
        } else {
            add(todo)
        }
    }

    fun remove(todo: Todo) {
        val positionOf = positionOf(todo)
        items.remove(todo)
        notifyItemRemoved(positionOf)
    }

    private fun positionOf(todo: Todo) = items.indexOf(todo)

    fun contains(todo: Todo): Boolean {
        return items.contains(todo)
    }

}
