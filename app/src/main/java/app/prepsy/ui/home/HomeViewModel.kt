package app.prepsy.ui.home

import androidx.lifecycle.*
import app.prepsy.domain.entities.SubjectWithYearsEntity
import app.prepsy.domain.usecases.ClearUserAnswersBySubjectAndYearUseCase
import app.prepsy.domain.usecases.GetIsTestInProgressUseCase
import app.prepsy.domain.usecases.GetSubjectWithYears
import app.prepsy.ui.mappers.Mapper
import app.prepsy.ui.models.SubjectWithYears
import app.prepsy.ui.models.Year
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSubjectYears: GetSubjectWithYears,
    private val getIsTestInProgressUseCase: GetIsTestInProgressUseCase,
    private val clearUserAnswersBySubjectAndYearUseCase: ClearUserAnswersBySubjectAndYearUseCase,
    private val subjectWithYearsMapper: Mapper<SubjectWithYears, SubjectWithYearsEntity>
): ViewModel() {

    private val years = MutableLiveData<List<Year>>()
    private val subjectWithYears = MutableLiveData<List<SubjectWithYears>>()

    fun getYears(): LiveData<List<Year>> = years
    fun getSubjectWithYears(): LiveData<List<SubjectWithYears>> = subjectWithYears

    init {
        // only get subjects that have questions
        viewModelScope.launch {
            val response = getSubjectYears.invoke()

            if (response.isEmpty()) return@launch

            val responseUi =
                response.map { subjectWithYearsMapper.from(it) }

            subjectWithYears.postValue(responseUi)
        }
    }

    fun getAvailableYearsForSubject(subjectId: String) {
        val localSubjectWithYears = subjectWithYears.value ?: return

        val localYears = localSubjectWithYears.map { it.years }
        val localSubjects = localSubjectWithYears.map { it.subject }

        val subjectIndex = localSubjects.indexOfFirst { subjectId == it.id }

        if (subjectIndex == -1) return

        years.postValue(localYears[subjectIndex])
    }

    fun isTestInProgress(subjectId: String, yearId: String): LiveData<Boolean> = liveData {
        val response = getIsTestInProgressUseCase(
            subjectId = subjectId,
            yearId = yearId
        )

        emit(response)
    }

    fun clearUserAnswersForTest(subjectId: String, yearId: String) = liveData<Boolean> {
        clearUserAnswersBySubjectAndYearUseCase(subjectId = subjectId, yearId = yearId)

        // if we are there, then the previous block has returned and is done running
        emit(true)
    }
}
