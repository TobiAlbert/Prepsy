package com.tobidaada.local.models

import androidx.room.*
import java.util.*

@Entity(
    tableName = "options",
    indices = [Index(name = "options_id_unique", unique = true, value = ["id"])],
    foreignKeys = [
        ForeignKey(
            entity = QuestionLocal::class,
            parentColumns = ["id"],
            childColumns = ["question_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class OptionLocal(
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
