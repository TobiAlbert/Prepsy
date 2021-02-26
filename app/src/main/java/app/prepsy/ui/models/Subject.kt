package app.prepsy.ui.models

data class Subject(
    val id: String,
    val name: String
) {
    override fun toString(): String = name
}