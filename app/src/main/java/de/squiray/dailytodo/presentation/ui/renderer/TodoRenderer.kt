package de.squiray.dailytodo.presentation.ui.renderer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pedrogomez.renderers.Renderer
import de.squiray.dailytodo.R

import de.squiray.dailytodo.domain.entity.Todo

class TodoRenderer : Renderer<Todo>() {


    override fun setUpView(rootView: View) {

    }

    override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View? {
        return inflater.inflate(R.layout.item_todo, parent, false)
    }

    override fun render() {

    }

    override fun hookListeners(rootView: View?) {
    }
}
