package realaof.realhon.realha.todulistapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import realaof.realhon.realha.todulistapp.data.service.todu.ToduService
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {

    @Provides
    @Singleton
    fun providesToduService(retrofit: Retrofit): ToduService =
        retrofit.create(ToduService::class.java)

}