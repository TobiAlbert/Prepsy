package app.prepsy.ui.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.prepsy.domain.usecases.GetQuestions
import app.prepsy.domain.usecases.SaveAnswer
import app.prepsy.ui.mappers.Mapper
import app.prepsy.ui.models.Option
import app.prepsy.ui.models.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import app.prepsy.domain.entities.OptionEntity as OptionEntity
import app.prepsy.domain.entities.QuestionEntity as QuestionEntity

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val saveUserAnswer: SaveAnswer,
    private val getQuestions: GetQuestions,
    private val answerMapper: Mapper<Option, OptionEntity>,
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

    fun saveAnswer(option: Option, questionId: String) =
        saveUserAnswer(answerMapper.to(option), questionId)
}