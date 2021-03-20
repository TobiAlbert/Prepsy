package com.tobidaada.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.tobidaada.local.models.QuestionAndOptions

@Dao
interface QuestionDao {

    @Transaction
    @Query("select * from questions where subject_id = :subjectId and year_id = :yearId")
    suspend fun getQuestionsBySubjectAndYear(
        subjectId: String,
        yearId: String
    ): List<QuestionAndOptions>
}