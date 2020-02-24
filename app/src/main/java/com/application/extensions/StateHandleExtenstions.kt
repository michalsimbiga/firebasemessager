package com.application.extensions

import androidx.lifecycle.SavedStateHandle
import timber.log.Timber
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


fun <T> SavedStateHandle.delegate(defaultVal: T) = object : ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, prop: KProperty<*>): T = get(prop.name) ?: defaultVal
    override fun setValue(thisRef: Any, prop: KProperty<*>, value: T) =
        set(prop.name, value).also { Timber.i("TESTING delegate save ${prop.name} $value") }
}
