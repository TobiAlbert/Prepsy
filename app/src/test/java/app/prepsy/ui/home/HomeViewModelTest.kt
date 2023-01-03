package app.prepsy.ui.home

import app.prepsy.common.domain.entities.SubjectWithYearsEntity
import app.prepsy.common.domain.usecases.ClearUserAnswersForSubject
import app.prepsy.common.domain.usecases.GetSubjects
import app.prepsy.ui.mappers.Mapper
import app.prepsy.ui.models.SubjectWithYears
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before


class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var getSubjectYearsMock: GetSubjects
    private lateinit var clearUserAnswersForSubjectMock: ClearUserAnswersForSubject
    private lateinit var subjectWithYearsMapperMock: Mapper<SubjectWithYears, SubjectWithYearsEntity>


    @Before
    fun setup() {
        getSubjectYearsMock = mockk()

        coEvery { getSubjectYearsMock.invoke() } returns emptyList()

        clearUserAnswersForSubjectMock = mockk()
        subjectWithYearsMapperMock = mockk()

//        viewModel = HomeViewModel(
//            getSubjectYearsMock,
//            isTestInProgressMock,
//            clearUserAnswersForSubjectMock,
//            subjectWithYearsMapperMock
//        )
    }
}