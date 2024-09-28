package app.prepsy.ui.home

import app.prepsy.ui.models.Subject
import app.prepsy.ui.models.Year

data class HomeViewState(
    val subjects: List<Subject> = emptyList(),
    val years: List<Year> = emptyList(),
    val selectedSubject: String = "",
    val selectedYear: String = ""
) {
    val isButtonEnabled: Boolean
        get() = subjects.isNotEmpty() && years.isNotEmpty()
}