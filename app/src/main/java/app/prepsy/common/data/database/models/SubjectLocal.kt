package app.prepsy.common.data.database.models

import androidx.room.*
import app.prepsy.common.domain.entities.SubjectEntity
import app.prepsy.common.domain.entities.SubjectWithYearsEntity
import app.prepsy.common.domain.entities.YearEntity
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
) {
    fun toDomain(): SubjectEntity =
        SubjectEntity(
            id = id,
            name = name
        )
}

@Entity(
    tableName = "subject_years",
    primaryKeys = ["subject_id", "year_id"],
    foreignKeys = [
        ForeignKey(
            entity = YearLocal::class,
            parentColumns = ["id"],
            childColumns = ["year_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SubjectLocal::class,
            parentColumns = ["id"],
            childColumns = ["subject_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
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

data class SubjectWithYearsLocal(
    @Embedded val subject: SubjectLocal,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity= YearLocal::class,
        associateBy = Junction(
            value = SubjectYearsCrossRef::class,
            parentColumn = "subject_id",
            entityColumn = "year_id"
        )
    )
    val years: List<YearLocal>
) {
    fun toDomain(): SubjectWithYearsEntity =
        SubjectWithYearsEntity(
            subject = subject.toDomain(),
            years = years.map(YearLocal::toDomain)
        )

}
