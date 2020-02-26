package com.application.extensions

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

fun Fragment.navigateTo(@IdRes resource: Int) = findNavController().navigate(resource)

fun Fragment.popBackstack() = findNavController().popBackStack()

fun Fragment.showSnack(@StringRes message: Int) {
    Snackbar.make(view!!, message, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.showSnack(message: String) {
    Snackbar.make(view!!, message, Snackbar.LENGTH_SHORT).show()
}