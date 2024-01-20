package realaof.realhon.realha.todulistapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import realaof.realhon.realha.todulistapp.data.local.ToduListAppDataBase
import realaof.realhon.realha.todulistapp.data.local.todu.ToduLocalDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDataBaseModule {

    @Provides
    @Singleton
    fun provideToduLocalAppDao(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ToduListAppDataBase::class.java,
        name = "toduList-database"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideToduLocalDao(toduListAppDataBase: ToduListAppDataBase): ToduLocalDao =
        toduListAppDataBase.toduLoccalDao()
}