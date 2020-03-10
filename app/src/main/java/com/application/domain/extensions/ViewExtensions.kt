package com.application.domain.extensions

import android.widget.EditText

fun EditText.clear() {
   setText(String.empty)
}