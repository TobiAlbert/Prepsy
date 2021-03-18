package com.tobidaada.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.tobidaada.local.models.Subject
import com.tobidaada.local.models.SubjectWithYears

@Dao
interface SubjectsDao {
    @Query("select * from subjects")
    fun getAll(): List<Subject>

    @Transaction
    @Query("select * from subjects where id = :subjectId")
    fun getYearsBySubjectId(subjectId: String): List<SubjectWithYears>
}