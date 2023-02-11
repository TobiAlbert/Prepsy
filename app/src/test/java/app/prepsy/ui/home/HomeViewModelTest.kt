package app.prepsy.ui.home

import app.prepsy.AppTestDispatcher
import app.prepsy.common.domain.entities.SubjectWithYearsEntity
import app.prepsy.common.domain.usecases.ClearUserAnswersForSubject
import app.prepsy.common.domain.usecases.GetSubjects
import app.prepsy.common.domain.usecases.GetYearsForSubject
import app.prepsy.common.domain.usecases.IsTestInProgress
import app.prepsy.managers.AppPreferences
import app.prepsy.managers.SharedPreferenceManagers
import app.prepsy.ui.mappers.Mapper
import app.prepsy.ui.models.SubjectWithYears
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before


class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val getSubjectYears: GetSubjects = mockk()
    private val getYearsForSubject: GetYearsForSubject = mockk()
    private val isTestInProgress: IsTestInProgress = mockk()
    private val preferenceManager: AppPreferences = mockk()
    private val clearUserAnswersForSubject: ClearUserAnswersForSubject = mockk()

    @Before
    fun setup() {

        coEvery { getSubjectYears.invoke() } returns emptyList()

        viewModel = HomeViewModel(
            getSubjectYears,
            getYearsForSubject,
            isTestInProgress,
            clearUserAnswersForSubject,
            preferenceManager,
            AppTestDispatcher
        )
    }
}