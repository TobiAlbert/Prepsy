package app.prepsy.di

import app.prepsy.common.data.repository.UtmeAnswerRepository
import app.prepsy.common.data.repository.UtmeQuestionRepository
import app.prepsy.common.data.repository.UtmeSubjectRepository
import app.prepsy.common.domain.repository.AnswerRepository
import app.prepsy.common.domain.repository.QuestionRepository
import app.prepsy.common.domain.repository.SubjectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindQuestionRepository(
        questionRepositoryImpl: UtmeQuestionRepository
    ): QuestionRepository

    @Binds
    abstract fun bindAnswerRepository(
        answerRepositoryImpl: UtmeAnswerRepository
    ): AnswerRepository

    @Binds
    abstract fun bindSubjectRepository(
        subjectRepositoryImpl: UtmeSubjectRepository
    ): SubjectRepository
}