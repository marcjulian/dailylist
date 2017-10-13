package de.squiray.dailytodo.presentation.ui.renderer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pedrogomez.renderers.Renderer
import de.squiray.dailytodo.R
import de.squiray.dailytodo.domain.entity.Todo

class TodoRenderer : Renderer<Todo>() {

    lateinit var todo_Title: TextView

    override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View? {
        return inflater.inflate(R.layout.item_todo, parent, false)
    }

    override fun render() {
        val todo: Todo = content
        renderTodo(todo)
    }

    private fun renderTodo(todo: Todo) {
        todo_Title.setText(todo.todo)
    }

    override fun setUpView(rootView: View) {
        todo_Title = rootView.findViewById(R.id.todo_title)
    }

    override fun hookListeners(rootView: View?) {
    }

}
