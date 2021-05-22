package com.tobidaada.local_.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.tobidaada.local.dao.QuestionDao
import com.tobidaada.local.dao.SubjectsDao
import com.tobidaada.local.db.AppDatabase
import com.tobidaada.local.models.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class QuestionDaoTest {

    private lateinit var db: AppDatabase

    private lateinit var dao: QuestionDao
    private lateinit var subjectDao: SubjectsDao

    private lateinit var subjectId: String
    private lateinit var yearId: String
    private lateinit var questionId: String

    private suspend fun initialiseData() {
        val date = Date()
        yearId = UUID.randomUUID().toString()
        subjectId = UUID.randomUUID().toString()
        questionId = UUID.randomUUID().toString()

        val year = YearLocal(
            id = yearId,
            name = "2004",
            createdAt = date,
            updatedAt = date
        )

        val subject = SubjectLocal(
            id = subjectId,
            name = "1",
            slug = "1",
            createdAt = date,
            updatedAt = date
        )

        val data = SubjectWithYearsLocal(
            subject = subject,
            years = listOf(year)
        )

        val rightOption = OptionLocal(
            id = "1",
            text = "loss",
            questionId = questionId,
            createdAt = date,
            updatedAt = date
        )

        // create options
        val options = listOf(
            rightOption,
            rightOption.copy(
                id = "2",
                text = "deficit"
            ),
            rightOption.copy(
                id = "3",
                text = "gain"
            ),
            rightOption.copy(
                id = "4",
                text = "surplus"
            )
        )

        // create question(s)
        val question = QuestionLocal(
            id = questionId,
            text = "Where the debit side of the income and " +
                    "expenditure account is higher than the credit side, " +
                    "the difference is?",
            subjectId = subjectId,
            yearId = yearId,
            rightOption = rightOption.id,
            createdAt = date,
            updatedAt = date
        )

        // insert questions with corresponding options
        subjectDao.insertSubjectWithYear(data)
        subjectDao.addQuestionAndOption(question, options)
    }

    @Before
    fun setup() = runBlocking {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.questionsDao()
        subjectDao = db.subjectsDao()

        initialiseData()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun testSubjectWithYearsIsNotEmpty() = runBlocking {
        val response = subjectDao.getSubjectsAndYears()

        assertThat(response).isNotEmpty()
    }

    @Test
    fun testCanQueryBySubjectIdAndYearId() = runBlocking {
        val response = dao.getQuestionsBySubjectAndYear(
            subjectId = subjectId, yearId = yearId
        )

        assertThat(response).hasSize(1)
    }

    @Test
    fun testSubjectAndYearIdsMatch() = runBlocking {
        val response = dao.getQuestionsBySubjectAndYear(
            subjectId = subjectId, yearId = yearId
        )

        val actualSubjectId = response.first().question.subjectId
        val actualYearId = response.first().question.yearId


        assertThat(actualSubjectId).isEqualTo(subjectId)
        assertThat(actualYearId).isEqualTo(yearId)
    }

    @Test
    fun testQuestionContainsRightOption() = runBlocking {
        val response = dao.getQuestionsBySubjectAndYear(
            subjectId = subjectId, yearId = yearId
        ).first()

        val optionsId = response.options.map { it.id }
        val rightOption = response.question.rightOption

        assertThat(optionsId).contains(rightOption)
    }

    @Test
    fun testQuestionHasFourOptions() = runBlocking {
        val response = dao.getQuestionsBySubjectAndYear(
            subjectId = subjectId, yearId = yearId
        ).first()

        val options = response.options
        assertThat(options).hasSize(4)
    }

    @Test
    fun testQuestionHasOnlyOneRightOption() = runBlocking {
        val response = dao.getQuestionsBySubjectAndYear(
            subjectId = subjectId, yearId = yearId
        ).first()

        val rightOption = response.question.rightOption
        val count = response.options.count { rightOption == it.id }

        assertThat(count).isEqualTo(1)
    }
}