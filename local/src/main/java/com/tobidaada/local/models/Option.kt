package com.tobidaada.local.models

import androidx.room.*
import java.util.*

@Entity(
    tableName = "options",
    indices = [Index(name = "options_id_unique", unique = true, value = ["id"])],
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
data class Option(
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
