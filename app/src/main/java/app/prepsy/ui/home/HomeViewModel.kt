package app.prepsy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.prepsy.domain.entities.SubjectWithYearsEntity
import app.prepsy.domain.usecases.GetAllSubjects
import app.prepsy.domain.usecases.GetSubjectWithYears
import app.prepsy.ui.mappers.Mapper
import app.prepsy.ui.models.Subject
import app.prepsy.ui.models.SubjectWithYears
import app.prepsy.ui.models.Year
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSubjectYears: GetSubjectWithYears,
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
}
