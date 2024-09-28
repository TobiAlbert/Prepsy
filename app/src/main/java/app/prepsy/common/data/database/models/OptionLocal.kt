package app.prepsy.common.data.database.models

import androidx.room.*
import app.prepsy.common.domain.entities.OptionEntity
import java.util.*

@Entity(
    tableName = "options",
    indices = [Index(name = "options_id_unique", unique = true, value = ["id"])],
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
) {
    fun toDomain(): OptionEntity =
        OptionEntity(
            id = id,
            text = text,
            questionId = questionId,
        )
}
