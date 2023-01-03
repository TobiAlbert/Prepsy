package app.prepsy.ui.home

sealed interface HomeViewAction {
    data class NavigateToQuestions(
        val subjectId: String,
        val yearId: String,
    ): HomeViewAction

    data class DisplayDialogPrompt(
        val subjectId: String,
        val yearId: String,
    ): HomeViewAction
}