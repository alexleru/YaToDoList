package ru.alexleru.ya.todolist

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.isHasText(afterChange: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(text: Editable?) {
           afterChange.invoke(text.toString())
        }
    })
}