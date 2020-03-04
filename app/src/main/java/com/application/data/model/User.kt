package com.application.data.model

import android.os.Parcelable
import com.application.domain.extensions.empty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val uid: String = String.empty,
    val username: String = String.empty,
    val email: String = String.empty,
    val profileImage: String = String.empty
) : Parcelable