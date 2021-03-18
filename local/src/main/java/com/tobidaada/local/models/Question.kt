package com.tobidaada.local.models

import androidx.room.*
import java.util.*

@Entity(tableName = "questions")
data class Question(
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
    val question: Question,

    @Relation(
        parentColumn = "id",
        entityColumn = "question_id"
    )
    val answer: Answer
)

data class QuestionAndOptions(
    @Embedded
    val question: Question,

    @Relation(
        parentColumn = "id",
        entityColumn = "question_id"
    )
    val options: List<Option>
)