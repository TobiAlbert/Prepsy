package app.prepsy.data.repository.question

import app.prepsy.data.models.Question

interface QuestionLocalDataSource {
    fun getQuestions(subjectId: String, yearId: String): List<Question>
}