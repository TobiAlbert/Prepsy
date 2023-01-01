package app.prepsy.ui.questions

import androidx.lifecycle.*
import app.prepsy.common.domain.entities.QuestionEntity
import app.prepsy.common.domain.entities.UserScoreEntity
import app.prepsy.common.domain.usecases.*
import app.prepsy.ui.mappers.Mapper
import app.prepsy.ui.models.Question
import app.prepsy.ui.models.UserScore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val saveUserAnswer: SaveAnswer,
    private val getQuestions: GetQuestions,
    private val getUserScore: GetUserScore,
    private val hasCompletedQuestions: HasCompletedQuestions,
    private val getObservableQuestions: GetObservableQuestionsUseCase,
    private val userScoreMapper: Mapper<UserScore, UserScoreEntity>,
    private val questionMapper: Mapper<Question, QuestionEntity>,
) : ViewModel() {

    private val questions = MutableLiveData<List<Question>>()

    fun getQuestions(): LiveData<List<Question>> = questions

    fun getTestQuestions(subjectId: String, yearId: String) {
        viewModelScope.launch {

            val questionResponse = getQuestions(subjectId, yearId).map { questionMapper.from(it) }
            questions.postValue(questionResponse)
        }
    }

    fun saveAnswer(questionId: String, optionId: String) {
        viewModelScope.launch {
            saveUserAnswer(questionId, optionId)
        }
    }

    fun calculateScore(subjectId: String, yearId: String): LiveData<UserScore> = liveData {
        val score: UserScoreEntity = getUserScore(subjectId, yearId)
        emit(userScoreMapper.from(score))
    }

    fun onSubmitClicked(subjectId: String, yearId: String): LiveData<Boolean> = liveData {
        val isComplete = hasCompletedQuestions(
            subjectId = subjectId,
            yearId = yearId
        )
        emit(isComplete)
    }

    fun getQuestionsFlow(subjectId: String, yearId: String): LiveData<List<Question>> {
        return getObservableQuestions(subjectId, yearId)
            .map { questionEntityList -> questionEntityList.map { questionMapper.from(it) } }.asLiveData()
    }
}