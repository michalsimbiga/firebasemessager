package com.application.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigateTo(@IdRes resource: Int) {
    findNavController().navigate(resource)
}

fun Fragment.popBackstack() {
    findNavController().popBackStack()
}