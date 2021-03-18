package com.tobidaada.local.models

import androidx.room.*
import java.util.*

@Entity(
    tableName = "answers",
    indices = [
        Index(name = "answers_id_unique", unique = true, value = ["id"]),
        Index(name = "answers_question_id_unique", unique = true, value = ["question_id"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = Question::class,
            parentColumns = ["id"],
            childColumns = ["question_id"],
            onDelete = ForeignKey.NO_ACTION,
            onUpdate = ForeignKey.NO_ACTION
        )
    ]
)
data class Answer(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "question_id")
    val questionId: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Date,

    @ColumnInfo(name ="updated_at")
    val updatedAt: Date
)