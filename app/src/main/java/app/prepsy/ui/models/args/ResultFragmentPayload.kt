package app.prepsy.ui.models.args

import android.os.Parcelable
import app.prepsy.ui.models.UserScore
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultFragmentPayload(
    val userScore: UserScore,
    val subjectId: String,
    val yearId: String
): Parcelable