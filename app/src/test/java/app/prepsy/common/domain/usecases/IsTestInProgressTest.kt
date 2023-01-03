package app.prepsy.common.domain.usecases

import app.prepsy.AppTestDispatcher
import app.prepsy.common.domain.entities.QuestionEntity
import app.prepsy.common.domain.repository.QuestionRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class IsTestInProgressTest {

    private lateinit var useCase: IsTestInProgress

    private val repo: QuestionRepository = mockk()
    private val dispatcher = AppTestDispatcher()

    private val question: QuestionEntity = mockk()

    @Before
    fun setup() {
        useCase = IsTestInProgress(
            repo = repo,
            appDispatcher = dispatcher
        )
    }

    @Test
    fun `test is not in progress if there are no questions`() = runTest {
        coEvery { repo.getQuestions(any(), any()) } returns emptyList()

        assertThat(invokeUseCase()).isFalse()
    }

    @Test
    fun `test is in progress if the user has attempted more than at least one question but not all`() =
        runTest {
            coEvery { repo.getQuestions(any(), any()) } returns MutableList(size = 10) { question }
            coEvery { repo.getUserAnswersCount(any(), any()) } returns 1

            assertThat(invokeUseCase()).isTrue()
        }

    @Test
    fun `test is not in progress is user has attempted all questions`() = runTest {
        coEvery { repo.getQuestions(any(), any()) } returns MutableList(size = 10) { question }
        coEvery { repo.getUserAnswersCount(any(), any()) } returns 10

        assertThat(invokeUseCase()).isFalse()
    }

    @Test
    fun `test is not in progress if user has attempted none of the questions`() = runTest {
        coEvery { repo.getQuestions(any(), any()) } returns MutableList(size = 10) { question }
        coEvery { repo.getUserAnswersCount(any(), any()) } returns 0

        assertThat(invokeUseCase()).isFalse()
    }

    private suspend fun invokeUseCase(): Boolean =
        useCase(
            subjectId = "subjectId",
            yearId = "yearId"
        )

}