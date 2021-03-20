package com.tobidaada.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.tobidaada.local.models.AnswerLocal

@Dao
interface AnswerDao {

    @Query("select * from answers where question_id = :questionId limit 1")
    suspend fun getAnswerByQuestionId(questionId: String): AnswerLocal
}