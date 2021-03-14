package app.prepsy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.prepsy.domain.entities.Subject as SubjectEntity
import app.prepsy.domain.usecases.GetAllSubjects
import app.prepsy.domain.usecases.GetSubjectYears
import app.prepsy.ui.mappers.Mapper
import app.prepsy.ui.models.Subject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllSubjectsUseCase: GetAllSubjects,
    private val getSubjectYears: GetSubjectYears,
    private val subjectMapper: Mapper<Subject, SubjectEntity>
): ViewModel() {

    private val subjects = MutableLiveData<List<Subject>>()
    private val years = MutableLiveData<List<String>>()

    fun getSubjects(): LiveData<List<Subject>> = subjects
    fun getYears(): LiveData<List<String>> = years

    init {
        getAllSubjectsUseCase.invoke().map { subjectMapper.from(it) }
            .also {
                subjects.postValue(it)
                it.let { s: List<Subject> ->
                    if (s.isNotEmpty()) {
                        getAvailableYearsForSubject(it.first().id)
                    }
                }
            }
    }

    fun getAvailableYearsForSubject(subjectId: String) {
        getSubjectYears(subjectId).map { it.toString() }
            .also { years.postValue(it) }
    }
}
