package app.prepsy.common.domain.entities

import app.prepsy.ui.models.Year

data class YearEntity(
    val id: String,
    val name: String
) {
    fun toUi(): Year =
        Year(
            id = id,
            name = name,
        )
}