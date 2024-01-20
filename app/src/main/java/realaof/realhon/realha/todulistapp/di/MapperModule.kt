package realaof.realhon.realha.todulistapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import realaof.realhon.realha.todulistapp.data.mapper.todu.ToduLocalDataMapper
import realaof.realhon.realha.todulistapp.data.mapper.todu.ToduLocalDataMapperImp
import realaof.realhon.realha.todulistapp.domain.mapper.todu.ToduMapper
import realaof.realhon.realha.todulistapp.domain.mapper.todu.TuduMapperImp

@InstallIn(SingletonComponent::class)
@Module
abstract class MapperModule {

    @Binds
    abstract fun providesToduLocalDataMapper(localMapperImp: ToduLocalDataMapperImp): ToduLocalDataMapper

    @Binds
    abstract fun providesToduMapper(mapperImp: TuduMapperImp): ToduMapper
}