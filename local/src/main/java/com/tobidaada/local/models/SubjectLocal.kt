package com.tobidaada.local.models

import androidx.room.*
import java.util.*

@Entity(
    tableName = "subjects",
    indices = [
        Index(name = "subjects_name_unique", unique = true, value = ["name"]),
        Index(name = "subjects_id_unique", unique = true, value = ["id"])
    ]
)
data class SubjectLocal(
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

@Entity(
    tableName = "subject_years",
    primaryKeys = ["subject_id", "year_id"],
    foreignKeys = [
        ForeignKey(
            entity = Year::class,
            parentColumns = ["id"],
            childColumns = ["year_id"],
            onDelete = ForeignKey.NO_ACTION,
            onUpdate = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = SubjectLocal::class,
            parentColumns = ["id"],
            childColumns = ["subject_id"],
            onDelete = ForeignKey.NO_ACTION,
            onUpdate = ForeignKey.NO_ACTION
        )
    ]
)
data class SubjectYearsCrossRef(
    @ColumnInfo(name = "subject_id")
    val subjectId: String,

    @ColumnInfo(name = "year_id")
    val yearId: String,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Date,

    @ColumnInfo(name = "created_at")
    val createdAt: Date
)

data class SubjectWithYears(
    @Embedded val subject: SubjectLocal,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity= Year::class,
        associateBy = Junction(
            value = SubjectYearsCrossRef::class,
            parentColumn = "subject_id",
            entityColumn = "year_id"
        )
    )
    val years: List<Year>
)
