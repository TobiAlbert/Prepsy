package app.prepsy.common.domain.entities

import app.prepsy.ui.models.Subject
import app.prepsy.utils.capitalizeWords

data class SubjectEntity(
    val id: String = "",
    val name: String
) {
    fun toUi(): Subject =
        Subject(
            id = id,
            name = name.capitalizeWords()
        )
}

data class SubjectWithYearsEntity(
    val subject: SubjectEntity,
    val years: List<YearEntity>
)