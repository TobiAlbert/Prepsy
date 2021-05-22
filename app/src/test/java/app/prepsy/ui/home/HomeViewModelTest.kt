package app.prepsy.ui.home

import app.prepsy.domain.entities.SubjectWithYearsEntity
import app.prepsy.domain.usecases.ClearUserAnswersBySubjectAndYearUseCase
import app.prepsy.domain.usecases.GetIsTestInProgressUseCase
import app.prepsy.domain.usecases.GetSubjectWithYears
import app.prepsy.ui.mappers.Mapper
import app.prepsy.ui.models.SubjectWithYears
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before


class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var getSubjectYearsMock: GetSubjectWithYears
    private lateinit var getIsTestInProgressUseCaseMock: GetIsTestInProgressUseCase
    private lateinit var clearUserAnswersBySubjectAndYearUseCaseMock: ClearUserAnswersBySubjectAndYearUseCase
    private lateinit var subjectWithYearsMapperMock: Mapper<SubjectWithYears, SubjectWithYearsEntity>


    @Before
    fun setup() {
        getSubjectYearsMock = mockk()

        coEvery { getSubjectYearsMock.invoke() } returns emptyList()

        getIsTestInProgressUseCaseMock = mockk()
        clearUserAnswersBySubjectAndYearUseCaseMock = mockk()
        subjectWithYearsMapperMock = mockk()

        viewModel = HomeViewModel(
            getSubjectYearsMock,
            getIsTestInProgressUseCaseMock,
            clearUserAnswersBySubjectAndYearUseCaseMock,
            subjectWithYearsMapperMock
        )
    }
}