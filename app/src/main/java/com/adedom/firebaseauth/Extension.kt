package com.adedom.firebaseauth

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun EditText.getContents() = this.text.toString().trim()

fun String.phoneNumberThai() = "+66${this.substring(1, 10)}"
