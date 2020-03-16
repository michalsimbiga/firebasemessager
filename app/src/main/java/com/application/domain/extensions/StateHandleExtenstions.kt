package com.application.domain.extensions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import timber.log.Timber
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> SavedStateHandle.delegate(defaultVal: T) = object : ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T =
        get(property.name) ?: defaultVal

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) =
        set(property.name, value).also { Timber.i("TESTING setValue $value")}
}

inline fun <reified T> SavedStateHandle.liveData(key: String? = null, initialValue: T) =
    object :
        ReadWriteProperty<Any, MutableLiveData<T>> {
        override fun setValue(thisRef: Any, property: KProperty<*>, value: MutableLiveData<T>) {
            val stateKey = key ?: property.name
            this@liveData[stateKey] = value
        }

        override fun getValue(thisRef: Any, property: KProperty<*>): MutableLiveData<T> {
            val stateKey = key ?: property.name
            return this@liveData.getLiveData(stateKey, initialValue)
        }
    }