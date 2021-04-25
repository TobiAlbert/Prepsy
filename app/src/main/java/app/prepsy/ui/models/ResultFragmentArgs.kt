package app.prepsy.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultFragmentPayload(
    val userScore: UserScore,
    val subjectId: String,
    val yearId: String
): Parcelable