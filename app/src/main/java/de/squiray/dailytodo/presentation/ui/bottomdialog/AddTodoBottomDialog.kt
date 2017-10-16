package de.squiray.dailytodo.presentation.ui.bottomdialog

import android.os.Bundle
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

    }

    override fun setupView() {

    }

}
