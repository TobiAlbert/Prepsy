package app.prepsy.ui.questions

import androidx.lifecycle.*
import app.prepsy.domain.entities.QuestionEntity
import app.prepsy.domain.entities.UserScoreEntity
import app.prepsy.domain.usecases.GetQuestions
import app.prepsy.domain.usecases.GetUserScore
import app.prepsy.domain.usecases.SaveAnswer
import app.prepsy.ui.mappers.Mapper
import app.prepsy.ui.models.Question
import app.prepsy.ui.models.UserScore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val saveUserAnswer: SaveAnswer,
    private val getQuestions: GetQuestions,
    private val getUserScore: GetUserScore,
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
        val score: UserScoreEntity  = getUserScore(subjectId, yearId)
        emit(userScoreMapper.from(score))
    }
}