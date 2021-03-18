package com.tobidaada.local.source.subject

import app.prepsy.data.models.SubjectData
import app.prepsy.data.repository.subject.SubjectLocalDataSource
import com.tobidaada.local.dao.SubjectsDao
import com.tobidaada.local.mapper.Mapper
import com.tobidaada.local.models.SubjectLocal
import javax.inject.Inject

class SubjectsLocalDataSourceImpl @Inject constructor (
    private val dao: SubjectsDao,
    private val subjectMapper: Mapper<SubjectData, SubjectLocal>
) : SubjectLocalDataSource {
    override suspend fun getAllSubjects(): List<SubjectData> = dao.getAll().map { subjectMapper.from(it) }

}