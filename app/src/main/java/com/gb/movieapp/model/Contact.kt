package com.gb.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    val contactId: Int,
    val name: String,
    val phoneNumber: List<String>,
) : Parcelable
