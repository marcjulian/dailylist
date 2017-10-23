package de.squiray.dailylist.presentation.ui.bottomdialog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import de.squiray.dailylist.R
import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.entity.TodoType
import de.squiray.dailylist.util.annotation.BottomSheetDialog

@BottomSheetDialog(layout = R.layout.layout_add_todo_bottom_dialog)
class AddOrChangeTodoBottomDialog : BaseBottomDialog<AddOrChangeTodoBottomDialog.Callback>() {
    companion object {
        private val ARG_TODO_TYPE: String = "todoType"
        private val ARG_TODO_ACTION: String = "todoAction"
        private val ARG_TODO: String = "todo"

        fun newInstance(type: TodoType): AddOrChangeTodoBottomDialog {
            val dialog = AddOrChangeTodoBottomDialog()
            val args = Bundle()
            args.putSerializable(ARG_TODO_TYPE, type)
            args.putSerializable(ARG_TODO_ACTION, TodoAction.ADD)
            dialog.arguments = args
            return dialog
        }

        fun newInstance(todo: Todo): AddOrChangeTodoBottomDialog {
            val dialog = AddOrChangeTodoBottomDialog()
            val args = Bundle()
            args.putSerializable(ARG_TODO_TYPE, todo.todoType)
            args.putSerializable(ARG_TODO_ACTION, TodoAction.CHANGE)
            args.putSerializable(ARG_TODO, todo)
            dialog.arguments = args
            return dialog
        }
    }

    enum class TodoAction {
        ADD, CHANGE
    }

    interface Callback : BaseCallback {
        fun onAddTodoClicked(todo: String, type: TodoType)
        fun onDeleteTodoClicked(todo: Todo)
        fun onSaveTodoClicked(todo: Todo, changeTodo: String)
    }

    lateinit var addTodoText: EditText

    override fun setupView(view: View) {
        addTodoText = view.findViewById(R.id.addTodoText)
        when (todoAction) {
            TodoAction.ADD -> setupForAdd(view)
            TodoAction.CHANGE -> setupForChange(view)
        }
    }

    private fun setupForChange(view: View) {
        val deleteTodo: Button = view.findViewById(R.id.deleteTodo)
        val saveTodo: Button = view.findViewById(R.id.saveTodo)

        saveTodo.visibility = VISIBLE
        deleteTodo.visibility = VISIBLE
        addTodoText.setText(todo.todo)
        addTodoText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable) {
                deleteTodo.isEnabled = !editable.toString().isEmpty()
                saveTodo.isEnabled = !editable.toString().isEmpty()
            }
        })

        deleteTodo.setOnClickListener {
            callback.onDeleteTodoClicked(todo)
            dismiss()
        }
        saveTodo.setOnClickListener {
            callback.onSaveTodoClicked(todo, addTodoText.text.toString())
            dismiss()
        }

    }

    private fun setupForAdd(view: View) {
        val addTodo: Button = view.findViewById(R.id.addTodo)
        addTodo.visibility = VISIBLE

        addTodoText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                callback.onAddTodoClicked(addTodoText.text.toString(), todoType)
                dismiss()
                true
            }
            false
        }

        addTodoText.hint = effectiveAddTodoHint(todoType)

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
            callback.onAddTodoClicked(addTodoText.text.toString(), todoType)
            dismiss()
        }
    }

    private fun effectiveAddTodoHint(todoType: TodoType): String {
        return getString(when (todoType) {
            TodoType.DAILY_TO_DO -> R.string.screen_todo_add_todo_daily_hint
            TodoType.FEAR -> R.string.screen_todo_add_todo_fear_hint
            TodoType.GOAL -> R.string.screen_todo_add_todo_goal_hint
        })
    }

    private val todoType: TodoType
        get() = arguments.getSerializable(ARG_TODO_TYPE) as TodoType

    private val todoAction: TodoAction
        get() = arguments.getSerializable(ARG_TODO_ACTION) as TodoAction

    private val todo: Todo
        get() = arguments.getSerializable(ARG_TODO) as Todo
}
