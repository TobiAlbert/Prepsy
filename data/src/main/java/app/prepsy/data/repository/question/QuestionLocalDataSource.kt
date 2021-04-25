package app.prepsy.data.repository.question

import app.prepsy.data.models.QuestionData
import app.prepsy.data.models.UserScoreData
import kotlinx.coroutines.flow.Flow

interface QuestionLocalDataSource {
    suspend fun  getQuestions(subjectId: String, yearId: String): List<QuestionData>

    suspend fun getUserScore(subjectId: String, yearId: String): UserScoreData

    suspend fun hasCompletedQuestions(subjectId: String, yearId: String): Boolean

    fun getObservableQuestions(subjectId: String, yearId: String): Flow<List<QuestionData>>
}