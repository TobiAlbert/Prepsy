package app.prepsy.di

import app.prepsy.data.mapper.AnswerDataMapper
import app.prepsy.data.mapper.Mapper
import app.prepsy.data.mapper.QuestionDataMapper
import app.prepsy.data.mapper.SubjectDataMapper
import app.prepsy.data.models.Option
import app.prepsy.data.models.Question
import app.prepsy.data.models.SubjectData
import app.prepsy.data.repository.answer.AnswerRepositoryImpl
import app.prepsy.data.repository.question.QuestionRepositoryImpl
import app.prepsy.data.repository.subject.SubjectRepositoryImpl
import app.prepsy.domain.entities.Subject as SubjectEntity
import app.prepsy.domain.repository.AnswerRepository
import app.prepsy.domain.repository.QuestionRepository
import app.prepsy.domain.repository.SubjectRepository
import app.prepsy.domain.entities.Question as QuestionEntity
import app.prepsy.domain.entities.Option as OptionEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindAnswerDataMapper(
        answerDataMapper: AnswerDataMapper
    ): Mapper<OptionEntity, Option>

    @Binds
    abstract fun bindQuestionDataMapper(
        questionDataMapper: QuestionDataMapper
    ): Mapper<QuestionEntity, Question>

    @Binds
    abstract fun bindSubjectDataMapper(
        subjectDataMapper: SubjectDataMapper
    ): Mapper<SubjectEntity, SubjectData>

    @Binds
    abstract fun bindQuestionRepository(
        questionRepositoryImpl: QuestionRepositoryImpl
    ): QuestionRepository

    @Binds
    abstract fun bindAnswerRepository(
        answerRepositoryImpl: AnswerRepositoryImpl
    ): AnswerRepository

    @Binds
    abstract fun bindSubjectRepository(
        subjectRepositoryImpl: SubjectRepositoryImpl
    ): SubjectRepository
}