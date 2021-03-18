package com.tobidaada.local.models

import androidx.room.*
import java.util.*

@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Date,

    @ColumnInfo(name ="updated_at")
    val updatedAt: Date
)

@Entity(tableName = "subject_years", primaryKeys = ["subject_id", "year_id"])
data class SubjectYearsCrossRef(
    @ColumnInfo(name = "subject_id")
    val subjectId: String,

    @ColumnInfo(name = "year_id")
    val yearId: String
)

data class SubjectWithYears(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subject_id",
        entityColumn = "year_id",
        associateBy = Junction(SubjectYearsCrossRef::class)
    )
    val years: List<Year>
)
