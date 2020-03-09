package com.application.data.model

import android.net.Uri
import android.os.Parcelable
import com.application.domain.extensions.empty
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val uid: String = String.empty,
    val username: String = String.empty,
    val email: String = String.empty,
    val profileImage: String = String.empty
) : Parcelable


fun FirebaseUser.toUser() = User(
    uid = uid,
    email = email ?: String.empty,
    profileImage = photoUrl.toString(),
    username = displayName ?: String.empty
)