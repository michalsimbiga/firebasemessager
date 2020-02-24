package com.application.extensions

import androidx.lifecycle.SavedStateHandle
import timber.log.Timber
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


fun <T> SavedStateHandle.delegate(defaultVal: T) = object : ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T =
        get(property.name) ?: defaultVal

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) =
        set(property.name, value).also { Timber.i("TESTING delegate save ${property.name} $value") }
}
