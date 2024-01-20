package realaof.realhon.realha.todulistapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import realaof.realhon.realha.todulistapp.domain.mapper.todu.ToduMapper
import realaof.realhon.realha.todulistapp.domain.mapper.todu.TuduMapperImp
import realaof.realhon.realha.todulistapp.domain.repository.todu.remote.ToduRepository
import realaof.realhon.realha.todulistapp.domain.repository.todu.remote.ToduRepositoryImp
import realaof.realhon.realha.todulistapp.domain.repository.todu.local.ToduLocalRepository
import realaof.realhon.realha.todulistapp.domain.repository.todu.local.ToduLocalRepositoryImp

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {


    @Binds
    abstract fun providesToduRepository(repositoryImp: ToduRepositoryImp): ToduRepository

    @Binds
    abstract fun prvidesToduLocalRepository(toduLocalRepositoryImp: ToduLocalRepositoryImp): ToduLocalRepository
}