package app.prepsy.data.repository.question

import app.prepsy.data.models.QuestionData

interface QuestionLocalDataSource {
    fun getQuestions(subjectId: String, yearId: String): List<QuestionData>
}