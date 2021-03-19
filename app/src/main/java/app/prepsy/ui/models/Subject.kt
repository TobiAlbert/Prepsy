package app.prepsy.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subject(
    val id: String,
    val name: String
): Parcelable {
    override fun toString(): String = name
}

data class SubjectWithYears(
    val subject: Subject,
    val years: List<Year>
)