package com.tobidaada.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.tobidaada.local.models.SubjectLocal
import com.tobidaada.local.models.SubjectWithYears

@Dao
interface SubjectsDao {
    @Query("select * from subjects")
    suspend fun getAll(): List<SubjectLocal>

    @Transaction
    @Query("select * from subjects where id = :subjectId")
    suspend fun getYearsBySubjectId(subjectId: String): List<SubjectWithYears>
}