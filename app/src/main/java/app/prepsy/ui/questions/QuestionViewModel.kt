package app.prepsy.ui.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.prepsy.domain.usecases.GetQuestions
import app.prepsy.domain.usecases.SaveAnswer
import app.prepsy.ui.mappers.Mapper
import app.prepsy.ui.models.Option
import app.prepsy.ui.models.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import app.prepsy.domain.entities.Option as OptionEntity
import app.prepsy.domain.entities.Question as QuestionEntity

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val saveUserAnswer: SaveAnswer,
    private val getQuestions: GetQuestions,
    private val answerMapper: Mapper<Option, OptionEntity>,
    private val questionMapper: Mapper<Question, QuestionEntity>,
) : ViewModel() {

    private val questions = MutableLiveData<List<Question>>()

    init {
        getTestQuestions("", "")
    }

    fun getQuestions(): LiveData<List<Question>> = questions

    private fun getTestQuestions(subjectId: String, yearId: String): List<Question> =
        getQuestions(subjectId, yearId).map { questionMapper.from(it) }
            .also { questions.postValue(it) }

    fun saveAnswer(option: Option, questionId: String) =
        saveUserAnswer(answerMapper.to(option), questionId)
}