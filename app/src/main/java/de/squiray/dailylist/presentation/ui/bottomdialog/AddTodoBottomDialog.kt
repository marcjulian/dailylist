package de.squiray.dailylist.presentation.ui.bottomdialog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import de.squiray.dailylist.R
import de.squiray.dailylist.domain.entity.TodoType
import de.squiray.dailylist.util.annotation.BottomSheetDialog

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
        addTodoText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                callback.onAddTodoClicked(addTodoText.text.toString(), todoType())
                dismiss()
                true
            }
            false
        }
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