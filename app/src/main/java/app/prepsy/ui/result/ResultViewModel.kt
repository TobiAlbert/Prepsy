package app.prepsy.ui.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import app.prepsy.common.domain.usecases.ClearUserAnswersForSubject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val clearUserAnswersForSubject: ClearUserAnswersForSubject,
): ViewModel() {


    fun clearUserAnswersForTest(subjectId: String, yearId: String) = liveData {
        clearUserAnswersForSubject(subjectId = subjectId, yearId = yearId)

        // if we are there, then the previous block has returned and is done running
        emit(true)
    }
}