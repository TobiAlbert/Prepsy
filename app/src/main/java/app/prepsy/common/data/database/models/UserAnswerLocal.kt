package app.prepsy.common.data.database.models

import androidx.room.*
import app.prepsy.common.domain.entities.UserAnswerEntity
import java.util.*

@Entity(
    tableName = "user_answers",
    indices = [
        Index(name = "user_answers_option_id_unique", unique = true, value = ["option_id"]),
        Index(name = "user_answers_question_id_unique", unique = true, value = ["question_id"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = QuestionLocal::class,
            parentColumns = ["id"],
            childColumns = ["question_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = OptionLocal::class,
            parentColumns = ["id"],
            childColumns = ["option_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class UserAnswerLocal(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "question_id")
    val questionId: String,

    @ColumnInfo(name = "option_id")
    val optionId: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Date,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Date
) {
    companion object {
        fun fromDomain(answer: UserAnswerEntity): UserAnswerLocal {
            val date = Date()
            return UserAnswerLocal(
                questionId = answer.questionId,
                optionId = answer.optionId,
                createdAt = date,
                updatedAt = date
            )
        }
    }

    fun toDomain(): UserAnswerEntity =
        UserAnswerEntity(
            questionId = questionId,
            optionId = optionId,
        )
}
