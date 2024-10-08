package app.prepsy.common.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import app.prepsy.common.domain.entities.YearEntity
import java.util.*

@Entity(
    tableName = "years",
    indices = [
        Index(name = "years_name_unique", unique = true, value = ["name"]),
        Index(name = "years_id_unique", unique = true, value = ["id"])
    ]
)
data class YearLocal(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Date,

    @ColumnInfo(name ="updated_at")
    val updatedAt: Date
) {
    fun toDomain(): YearEntity =
        YearEntity(
            id = id,
            name = name
        )
}
