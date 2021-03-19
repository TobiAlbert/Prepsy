package com.tobidaada.local.models

import androidx.room.*
import java.util.*

@Entity(
    tableName = "questions",
    indices = [Index(name = "questions_id_unique", unique = true, value = ["id"])],
    foreignKeys = [
        ForeignKey(
            entity = SubjectLocal::class,
            parentColumns = ["id"],
            childColumns = ["subject_id"],
            onDelete = ForeignKey.NO_ACTION,
            onUpdate = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = YearLocal::class,
            parentColumns = ["id"],
            childColumns = ["year_id"],
            onDelete = ForeignKey.NO_ACTION,
            onUpdate = ForeignKey.NO_ACTION
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

    @ColumnInfo(name = "created_at")
    val createdAt: Date,

    @ColumnInfo(name ="updated_at")
    val updatedAt: Date
)

data class QuestionAndAnswer(
    @Embedded
    val question: QuestionLocal,

    @Relation(
        parentColumn = "id",
        entityColumn = "question_id"
    )
    val answer: AnswerLocal
)

data class QuestionAndOptions(
    @Embedded
    val question: QuestionLocal,

    @Relation(
        parentColumn = "id",
        entityColumn = "question_id"
    )
    val options: List<OptionLocal>
)