package com.application.domain.extensions

import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


fun <T> SavedStateHandle.delegate(defaultVal: T) = object : ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T =
        get(property.name) ?: defaultVal

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) =
        set(property.name, value)
}