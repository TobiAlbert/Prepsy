package app.prepsy.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UserScore (
    val score: Int,
    val total: Int
): Parcelable