package app.prepsy.ui.models

data class Year(
    val id: String,
    val name: String
) {
    override fun toString(): String = name
}