package app.prepsy.ui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Option(
    val alphabet: String,
    val text: String
): Parcelable

@Parcelize
data class Question(
    val text: String,
    val options: List<Option>,
    val answer: Option
): Parcelable