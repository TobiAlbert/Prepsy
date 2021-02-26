package app.prepsy.data.repository.subject

import app.prepsy.domain.entities.Subject
import app.prepsy.domain.repository.SubjectRepository
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor() : SubjectRepository {

    override fun getAllSubjects(): List<Subject> = listOf(
        Subject(name = "Agriculture"),
        Subject(name = "Arabic"),
        Subject(name = "Art"),
        Subject(name = "Biology"),
        Subject(name = "Chemistry"),
        Subject(name = "CRS"),
        Subject(name = "Commerce"),
        Subject(name = "Economics"),
        Subject(name = "French"),
        Subject(name = "Geography"),
        Subject(name = "Government"),
        Subject(name = "Hausa"),
        Subject(name = "History"),
        Subject(name = "Home Economics"),
        Subject(name = "Igbo"),
        Subject(name = "Islamic Studies"),
        Subject(name = "Literature in English"),
        Subject(name = "Mathematics"),
        Subject(name = "Music"),
        Subject(name = "Physics"),
        Subject(name = "Principles of Accounts"),
        Subject(name = "Use of English"),
        Subject(name = "Yoruba")
    )

    override fun getYearsForSubject(subjectId: String): List<Int> = listOf(
        2010, 2011, 2012, 2013, 2014, 2015,
        2016, 2017, 2018, 2019, 2020
    )

}