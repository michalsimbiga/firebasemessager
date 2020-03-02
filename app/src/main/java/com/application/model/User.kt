package com.application.model

import android.os.Parcelable
import com.application.extensions.empty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val username: String = String.empty,
    val email: String = String.empty,
    val profileImage: String = String.empty
) : Parcelable