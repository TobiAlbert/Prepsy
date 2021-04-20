package app.prepsy.data.repository.question

import app.prepsy.data.models.QuestionData
import app.prepsy.data.models.UserScoreData

interface QuestionLocalDataSource {
    suspend fun  getQuestions(subjectId: String, yearId: String): List<QuestionData>

    suspend fun getUserScore(subjectId: String, yearId: String): UserScoreData
}