package app.prepsy.ui.models.args

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// kotlin parcelable annotation currently doesn't
// work with sealed classes.
enum class QuestionPageMode {
    QUESTION_MODE,
    VIEW_ANSWER_MODE
}

@Parcelize
data class QuestionPageFragmentPayload(
    val mode: QuestionPageMode,
    val subjectId: String,
    val yearId: String
): Parcelable