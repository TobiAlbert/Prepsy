package app.prepsy.common.data.database.models

import androidx.room.*
import app.prepsy.common.domain.entities.QuestionEntity
import java.util.*

@Entity(
    tableName = "questions",
    indices = [Index(name = "questions_id_unique", unique = true, value = ["id"])],
    foreignKeys = [
        ForeignKey(
            entity = SubjectLocal::class,
            parentColumns = ["id"],
            childColumns = ["subject_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = YearLocal::class,
            parentColumns = ["id"],
            childColumns = ["year_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = OptionLocal::class,
            parentColumns = ["id"],
            childColumns = ["right_option"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class QuestionLocal(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "subject_id")
    val subjectId: String,

    @ColumnInfo(name = "year_id")
    val yearId: String,

    @ColumnInfo(name = "right_option")
    val rightOption: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Date,

    @ColumnInfo(name ="updated_at")
    val updatedAt: Date
)

data class QuestionOptionsUserAnswer(
    @Embedded
    val question: QuestionLocal,

    @Relation(
        parentColumn = "id",
        entityColumn = "question_id"
    )
    val options: List<OptionLocal>,

    @Relation(
        parentColumn = "id",
        entityColumn = "question_id"
    )
    val userAnswer: UserAnswerLocal? = null
) {
    fun toDomain(): QuestionEntity {
        val answer = options.first { it.id ==  question.rightOption }

        return QuestionEntity(
            id = question.id,
            text = question.text,
            userOptionId = userAnswer?.optionId,
            options = options.map(OptionLocal::toDomain),
            answer = answer.toDomain(),
        )
    }
}