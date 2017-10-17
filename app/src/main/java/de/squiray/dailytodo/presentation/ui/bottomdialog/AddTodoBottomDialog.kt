package de.squiray.dailytodo.presentation.ui.bottomdialog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import de.squiray.dailytodo.R
import de.squiray.dailytodo.domain.entity.TodoType
import de.squiray.dailytodo.util.annotation.BottomSheetDialog

@BottomSheetDialog(layout = R.layout.layout_add_todo_bottom_dialog)
class AddTodoBottomDialog : BaseBottomDialog<AddTodoBottomDialog.Callback>() {
    companion object {
        private val ARG_TODO_TYPE: String = "todoType"

        fun newInstance(type: TodoType): AddTodoBottomDialog {
            val dialog = AddTodoBottomDialog()
            val args = Bundle()
            args.putSerializable(ARG_TODO_TYPE, type)
            dialog.arguments = args
            return dialog
        }
    }

    interface Callback : BaseCallback {
        fun onAddTodoClicked(todo: String, type: TodoType)
    }

    override fun setupView(view: View) {
        val addTodoText: EditText = view.findViewById(R.id.addTodoText)
        val addTodo: Button = view.findViewById(R.id.addTodo)

        addTodoText.hint = effectiveAddTodoHint(todoType())

        addTodoText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable) {
                addTodo.isEnabled = !editable.toString().isEmpty()
            }

        })
        addTodo.setOnClickListener {
            callback.onAddTodoClicked(addTodoText.text.toString(), todoType())
            dismiss()
        }
    }

    private fun effectiveAddTodoHint(todoType: TodoType): String {
        return getString(when (todoType) {
            TodoType.DAILY_TO_DO ->
                R.string.screen_todo_add_todo_daily_hint
            TodoType.FEAR ->
                R.string.screen_todo_add_todo_fear_hint
            TodoType.GOAL ->
                R.string.screen_todo_add_todo_goal_hint
        })
    }

    private fun todoType(): TodoType = arguments.getSerializable(ARG_TODO_TYPE) as TodoType
}
