package app.prepsy.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.prepsy.common.domain.entities.SubjectEntity
import app.prepsy.common.domain.entities.YearEntity
import app.prepsy.common.domain.usecases.ClearUserAnswersForSubject
import app.prepsy.common.domain.usecases.GetSubjects
import app.prepsy.common.domain.usecases.GetYearsForSubject
import app.prepsy.common.domain.usecases.IsTestInProgress
import app.prepsy.managers.SharedPreferenceManagers
import app.prepsy.utils.AppDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSubjects: GetSubjects,
    private val getYearsForSubject: GetYearsForSubject,
    private val isTestInProgress: IsTestInProgress,
    private val clearUserAnswersForSubject: ClearUserAnswersForSubject,
    private val preferenceManager: SharedPreferenceManagers,
    private val appDispatcher: AppDispatcher,
) : ViewModel() {

    val state: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState())
    val action: MutableSharedFlow<HomeViewAction> = MutableSharedFlow()

    private var subjectId: String = ""
    private var yearId: String = ""

    init {
        viewModelScope.launch(appDispatcher.main) {
            val response = getSubjects.invoke()

            if (response.isEmpty()) return@launch

            val subjects = response.map(SubjectEntity::toUi)

            state.value = state.value.copy(subjects = subjects)

            onSubjectSelected(position = 0)
        }
    }

    private fun getAvailableYearsForSubject(subjectId: String) {
        viewModelScope.launch(appDispatcher.main) {
            val years = getYearsForSubject(subjectId).map(YearEntity::toUi)

            state.value = state.value.copy(years = years)

            onYearSelected(position = 0)
        }
    }

    fun viewQuestions() {
        viewModelScope.launch(appDispatcher.main) {
            val isInProgress = isTestInProgress(
                subjectId = subjectId,
                yearId = yearId,
            )

            val viewAction = if (isInProgress) {
                HomeViewAction.DisplayDialogPrompt(
                    subjectId = subjectId,
                    yearId = yearId,
                )
            } else {
                HomeViewAction.NavigateToQuestions(
                    subjectId = subjectId,
                    yearId = yearId,
                )
            }

            action.emit(viewAction)
        }
    }

    fun clearUserAnswersForTest(subjectId: String, yearId: String) {
        viewModelScope.launch(appDispatcher.main) {

            clearUserAnswersForSubject(subjectId = subjectId, yearId = yearId)

            navigateToQuestions(
                subjectId = subjectId,
                yearId = yearId,
            )
        }
    }

    fun navigateToQuestions(subjectId: String, yearId: String) {
        saveTestChoice()
        viewModelScope.launch(appDispatcher.main) {
            val viewAction = HomeViewAction.NavigateToQuestions(
                subjectId = subjectId,
                yearId = yearId,
            )
            action.emit(viewAction)
        }
    }

    fun onSubjectSelected(position: Int) {
        val isValidPosition = position in 0..state.value.subjects.lastIndex

        if (!isValidPosition) {
            return
        }

        val subject = state.value.subjects[position]

        subjectId = subject.id

        state.value = state.value.copy(selectedSubject = subject.name)

        getAvailableYearsForSubject(subject.id)
    }

    fun onYearSelected(position: Int) {
        val isValidPosition = position in 0..state.value.years.lastIndex

        if (!isValidPosition) {
            return
        }

        val year = state.value.years[position]

        yearId = year.id

        state.value = state.value.copy(selectedYear = year.name)
    }

    private fun saveTestChoice() {
        preferenceManager.saveString(
            SharedPreferenceManagers.LAST_SELECTED_SUBJECT_ID,
            subjectId
        )
        preferenceManager.saveString(
            SharedPreferenceManagers.LAST_SELECTED_YEAR_ID,
            yearId
        )
    }
}
