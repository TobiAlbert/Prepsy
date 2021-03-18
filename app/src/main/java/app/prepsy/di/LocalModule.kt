package app.prepsy.di

import android.content.Context
import androidx.room.Room
import app.prepsy.data.models.SubjectData
import app.prepsy.data.repository.subject.SubjectLocalDataSource
import com.tobidaada.local.dao.SubjectsDao
import com.tobidaada.local.db.AppDatabase
import com.tobidaada.local.mapper.Mapper
import com.tobidaada.local.mapper.SubjectLocalDataMapper
import com.tobidaada.local.models.SubjectLocal
import com.tobidaada.local.source.subject.SubjectsLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModuleBinds {
    @Binds
    abstract fun bindLocalModuleSubjectMapper(
        mapper: SubjectLocalDataMapper
    ): Mapper<SubjectData, SubjectLocal>

    @Binds
    abstract fun bindLocalSubjectDataSourceImpl(
        dataSource: SubjectsLocalDataSourceImpl
    ): SubjectLocalDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app.db"
        )
        .createFromAsset("database/asset.db")
        .build()

    @Provides
    @Singleton
    fun provideSubjectsDao(db: AppDatabase): SubjectsDao = db.subjectsDao()

}